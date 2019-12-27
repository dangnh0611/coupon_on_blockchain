struct Bearer:
    acquired: bool
    transfered: bool
    redeemed: bool
    distributor: address

struct Distributor:
    _address: address 
    num_acquired: uint256
    num_redeemed: uint256
    received: wei_value

struct CampainDetail:
    name: string[50]
    category: string[20]
    description: string[100]
    end_time: timestamp
    total: uint256
    remain: uint256
    num_redeemed: uint256
    wei_per_redeemtion: wei_value

AddDistributor: event({_distributor: address})
RemoveDistributor: event({_distributor: address})
TransferBearer: event({_from: indexed(address), _to: indexed(address)})
Acquire: event({_address: indexed(address), _distributor: indexed(address)})
Redeem: event({_address: indexed(address), _distributor: indexed(address)})
GetSalary: event({_address: indexed(address), _amount: wei_value})
Refund: event({_amount: wei_value})

MAX_DISTRIBUTOR: constant(uint256) = 50

is_initialized: bool
cur_distributors: uint256
wei_per_redeemtion: wei_value
issuer: address
num_coupons: uint256
remain_coupons: uint256
num_redeemed: uint256
end_time: timestamp
refund: wei_value
distributors: Distributor[MAX_DISTRIBUTOR]
bearers: map(address, Bearer)
name: string[50]
category: string[20]
description: string[100]
is_free: bool

@public
@payable
def __init__(_issuer: address,_is_free_from_issuer: bool, _num_coupons: uint256, _wei_per_redeemtion: wei_value, _time_limit: timedelta, _name: string[50], _category: string[20], _description: string[100]):
    self.is_initialized=True
    self.refund=_wei_per_redeemtion*_num_coupons
    assert msg.value >=self.refund
    self.name=_name
    self.category=_category
    self.description=_description
    self.is_free= _is_free_from_issuer
    self.end_time= block.timestamp + _time_limit
    self.issuer= _issuer
    self.num_coupons=_num_coupons
    self.wei_per_redeemtion= _wei_per_redeemtion
    self.remain_coupons= self.num_coupons
    self.distributors[0]=Distributor({_address: _issuer, num_acquired: 0, num_redeemed: 0, received: 0})
    self.cur_distributors=0

# @nonreentrant('lock')
@public
@payable
def initialize(_issuer: address, _is_free_from_issuer: bool, _num_coupons: uint256, _wei_per_redeemtion: wei_value, _time_limit: timedelta, _name: string[50], _category: string[20], _description: string[100]):
    assert not(self.is_initialized)
    self.is_initialized=True
    self.refund=_wei_per_redeemtion*_num_coupons
    self.name=_name
    self.category=_category
    self.description=_description
    self.is_free=_is_free_from_issuer
    self.end_time= block.timestamp + _time_limit
    self.issuer= _issuer
    self.num_coupons=_num_coupons
    self.wei_per_redeemtion= _wei_per_redeemtion
    self.remain_coupons= self.num_coupons
    self.distributors[0]=Distributor({_address: _issuer, num_acquired: 0, num_redeemed: 0, received: 0})
    self.cur_distributors=0

@public
@constant
def get_distributors_detail(_idx: uint256) -> Distributor:
    return self.distributors[_idx]

@public
@constant
def get_campain_detail() -> CampainDetail:
    return CampainDetail({name:self.name, category: self.category, description: self.description, end_time: self.end_time, total: self.num_coupons, remain: self.remain_coupons,num_redeemed: self.num_redeemed, wei_per_redeemtion: self.wei_per_redeemtion })

@public
def add_distributor(_new_distributor: address) -> bool:
    if msg.sender!=self.issuer:
        return False
    if self.cur_distributors == MAX_DISTRIBUTOR:
        return False
    suit_idx: int128 = -1
    for i in range(MAX_DISTRIBUTOR):
        if (suit_idx==-1) and (self.distributors[i]._address==ZERO_ADDRESS):
            suit_idx=i 
        elif self.distributors[i]._address==_new_distributor:
            return False
        else: 
            continue
    if suit_idx!=-1:
        self.distributors[suit_idx]=Distributor({_address: _new_distributor, num_acquired:0, num_redeemed:0, received: 0})
        log.AddDistributor(_new_distributor)
        return True
    return False

@public
def remove_distributor(_target_distributor: address) -> bool:
    if msg.sender != self.issuer:
        return False
    for i in range(MAX_DISTRIBUTOR):
        if self.distributors[i]._address==_target_distributor:
            if self.distributors[i].num_acquired!=0:
                return False
            else:
                clear(self.distributors[i])
                log.RemoveDistributor(_target_distributor)
                return True
    return False

@public 
def transfer_coupon(_dest_address: address) -> bool:
    if block.timestamp > self.end_time:
        return False
    if self.bearers[_dest_address].acquired==True:
        return False
    if (self.bearers[msg.sender].acquired==True) and (self.bearers[msg.sender].transfered==False) and (self.bearers[msg.sender].redeemed== False):
        self.bearers[msg.sender].transfered=True
        self.bearers[_dest_address]=Bearer({acquired: True, transfered: False, redeemed: False, distributor: self.bearers[msg.sender].distributor})
        log.TransferBearer(msg.sender, _dest_address)
        return True
    else:
        return False

@private
def update_acquire(_bearer: address, _distributor: address) -> bool:
    self.remain_coupons-=1
    for i in range(MAX_DISTRIBUTOR):
        if self.distributors[i]._address==_distributor:
            self.distributors[i].num_acquired+=1
            break
    self.bearers[_bearer]=Bearer({acquired: True, transfered: False, redeemed: False, distributor: _distributor})
    log.Acquire(_bearer, _distributor)
    return True

@private
def update_redeem(_bearer: address, _distributor: address) -> bool:
    self.num_redeemed+=1
    for i in range(MAX_DISTRIBUTOR):
        if self.distributors[i]._address==_distributor:
            self.distributors[i].num_redeemed+=1
            break
    self.bearers[_bearer].redeemed=True
    log.Redeem(_bearer, _distributor)
    return True

@public
def withdraw():
    assert self.wei_per_redeemtion > 0
    for i in range(MAX_DISTRIBUTOR):
        if self.distributors[i]._address==msg.sender:
            _num_redeemed: uint256 = self.distributors[i].num_redeemed
            if _num_redeemed>0:
                salary: wei_value=self.wei_per_redeemtion*_num_redeemed - self.distributors[i].received
                assert salary > 0
                send(msg.sender, salary)
                log.GetSalary(msg.sender, salary)
                self.distributors[i].received+=salary
        break

@public
def final_refund():
    assert block.timestamp>self.end_time
    assert msg.sender==self.issuer
    assert self.refund>0
    send(self.issuer, self.refund)
    log.Refund(self.refund)

@public
def free_acquire_from_issuer() -> bool:
    if (block.timestamp > self.end_time):
        return False
    if msg.sender ==self.issuer:
        return False
    if self.remain_coupons==0:
        return False
    return self.update_acquire(msg.sender, self.issuer)
    
@public
def acquire(_hash: bytes32, v: uint256, r: uint256, s: uint256, _distributor: address) -> bool:
    if (block.timestamp > self.end_time):
        return False
    if self.remain_coupons==0:
        return False
    exist_distributor: bool = False
    for i in range(MAX_DISTRIBUTOR):
        if self.distributors[i]._address==_distributor:
            exist_distributor=True
            break
    if not(exist_distributor):
        return False
    if msg.sender==self.issuer:
        return False
    if _hash != keccak256(concat(convert(msg.sender,bytes32),convert(self, bytes32))):
        return False
    if (self.bearers[msg.sender].acquired==True) or (self.bearers[msg.sender].transfered==True):
        return False
    if ecrecover(_hash, v, r, s)==_distributor:
        return self.update_acquire(msg.sender, _distributor)
    return False

@public
def redeem(_bearer: address, _hash: bytes32, v: uint256, r: uint256, s: uint256) -> bool:
    if block.timestamp > self.end_time:
        return False
    if _bearer==self.issuer:
        return False
    if _hash != keccak256(concat(convert(_bearer,bytes32),convert(self, bytes32))):
        return False
    if (self.bearers[_bearer].transfered==False) and (self.bearers[_bearer].acquired==True) and (self.bearers[_bearer].redeemed==False) and (ecrecover(_hash, v, r, s)==_bearer):
        return self.update_redeem(_bearer, self.bearers[_bearer].distributor)
    return False
