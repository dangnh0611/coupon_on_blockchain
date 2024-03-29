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

contract CampainFactory:
    def log_acquire(_bearer: address, _end_time: timestamp): modifying

    def log_redeem(_bearer: address): modifying

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
factory_address: public(address)

@public
@payable
def __init__(_issuer: address,_is_free_from_issuer: bool, _num_coupons: uint256, _wei_per_redeemtion: wei_value, _end_time: timestamp, _name: string[50], _category: string[20], _description: string[100]):
    self.is_initialized=True
    self.refund=_wei_per_redeemtion*_num_coupons
    assert msg.value >=self.refund
    self.name=_name
    self.category=_category
    self.description=_description
    self.is_free= _is_free_from_issuer
    self.end_time= _end_time
    self.issuer= _issuer
    self.num_coupons=_num_coupons
    self.wei_per_redeemtion= _wei_per_redeemtion
    self.remain_coupons= self.num_coupons
    self.distributors[0]=Distributor({_address: _issuer, num_acquired: 0, num_redeemed: 0, received: 0})
    self.cur_distributors=1

# @nonreentrant('lock')
@public
@payable
def initialize(_issuer: address, _is_free_from_issuer: bool, _num_coupons: uint256, _wei_per_redeemtion: wei_value, _end_time: timestamp, _name: string[50], _category: string[20], _description: string[100]):
    assert not(self.is_initialized)
    self.is_initialized=True
    self.refund=_wei_per_redeemtion*_num_coupons
    self.name=_name
    self.category=_category
    self.description=_description
    self.is_free=_is_free_from_issuer
    self.end_time= _end_time
    self.issuer= _issuer
    self.num_coupons=_num_coupons
    self.wei_per_redeemtion= _wei_per_redeemtion
    self.remain_coupons= self.num_coupons
    self.distributors[0]=Distributor({_address: _issuer, num_acquired: 0, num_redeemed: 0, received: 0})
    self.cur_distributors=1
    self.factory_address = msg.sender

@public
@constant
def get_distributors_address(_idx: uint256) -> address:
    return self.distributors[_idx]._address

@public
@constant
def get_distributors_status(_idx: uint256) -> uint256[2]:
    return [self.distributors[_idx].num_acquired, self.distributors[_idx].num_redeemed]

@public
@constant
def get_distributors_withdrawed(_idx: uint256) -> wei_value:
    return self.distributors[_idx].received

@public
@constant
def get_bearer_status(_address: address) -> bool[3]:
    b: Bearer = self.bearers[_address]
    return [b.acquired, b.transfered, b.redeemed]

@public
@constant
def get_campain_name() -> string[50]:
    return self.name

@public
@constant
def get_campain_category() -> string[20]:
    return self.category

@public
@constant
def get_campain_description() -> string[100]:
    return self.description

@public
@constant
def get_campain_endtime() ->timestamp:
    return self.end_time

@public
@constant
def get_wei_per_redeemed() -> wei_value: 
    return self.wei_per_redeemtion

@public
@constant
def get_campain_status() -> uint256[3]:
    return [self.num_coupons, self.remain_coupons, self.num_redeemed]

@public
def add_distributor(_new_distributor: address):
    assert msg.sender == self.issuer
    assert self.cur_distributors < MAX_DISTRIBUTOR
    suit_idx: int128 = -1
    for i in range(MAX_DISTRIBUTOR):
        assert self.distributors[i]._address != _new_distributor
        if (suit_idx==-1) and (self.distributors[i]._address==ZERO_ADDRESS):
            suit_idx=i
    if suit_idx!=-1:
        self.distributors[suit_idx]=Distributor({_address: _new_distributor, num_acquired:0, num_redeemed:0, received: 0})
        log.AddDistributor(_new_distributor)

@public
def remove_distributor(_target_distributor: address):
    assert msg.sender == self.issuer
    for i in range(MAX_DISTRIBUTOR):
        if self.distributors[i]._address==_target_distributor:
            assert self.distributors[i].num_acquired == 0
            clear(self.distributors[i])
            log.RemoveDistributor(_target_distributor)

@public 
def transfer_coupon(_dest_address: address):
    assert block.timestamp <= self.end_time
    assert not(self.bearers[_dest_address].acquired)
    assert self.bearers[msg.sender].acquired
    assert not(self.bearers[msg.sender].transfered)
    assert not(self.bearers[msg.sender].redeemed)
    self.bearers[msg.sender].transfered=True
    self.bearers[_dest_address]=Bearer({acquired: True, transfered: False, redeemed: False, distributor: self.bearers[msg.sender].distributor})
    log.TransferBearer(msg.sender, _dest_address)
    
@private
def update_acquire(_bearer: address, _distributor: address):
    self.remain_coupons-=1
    for i in range(MAX_DISTRIBUTOR):
        if self.distributors[i]._address==_distributor:
            self.distributors[i].num_acquired+=1
            break
    self.bearers[_bearer]=Bearer({acquired: True, transfered: False, redeemed: False, distributor: _distributor})
    log.Acquire(_bearer, _distributor)
    CampainFactory(self.factory_address).log_acquire(_bearer, self.end_time)

@private
def update_redeem(_bearer: address, _distributor: address):
    self.num_redeemed+=1
    for i in range(MAX_DISTRIBUTOR):
        if self.distributors[i]._address==_distributor:
            self.distributors[i].num_redeemed+=1
            break
    self.bearers[_bearer].redeemed=True
    log.Redeem(_bearer, _distributor)
    CampainFactory(self.factory_address).log_redeem(_bearer)

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
def free_acquire_from_issuer():
    assert block.timestamp <= self.end_time
    assert msg.sender != self.issuer
    assert self.remain_coupons > 0
    self.update_acquire(msg.sender, self.issuer)
    
@public
def acquire(_hash: bytes32, v: uint256, r: uint256, s: uint256, _distributor: address):
    assert block.timestamp <= self.end_time
    assert self.remain_coupons > 0
    exist_distributor: bool = False
    for i in range(MAX_DISTRIBUTOR):
        if self.distributors[i]._address==_distributor:
            exist_distributor=True
            break
    assert exist_distributor
    assert msg.sender != self.issuer
    assert _hash == keccak256(concat(convert(msg.sender,bytes32),convert(self, bytes32)))
    assert not(self.bearers[msg.sender].acquired)
    assert not(self.bearers[msg.sender].transfered)
    assert ecrecover(_hash, v, r, s)==_distributor
    self.update_acquire(msg.sender, _distributor)

@public
def redeem(_bearer: address, _hash: bytes32, v: uint256, r: uint256, s: uint256):
    assert block.timestamp <= self.end_time
    assert msg.sender == self.issuer
    assert _hash == keccak256(concat(convert(_bearer,bytes32),convert(self, bytes32)))
    assert not(self.bearers[_bearer].transfered)
    assert self.bearers[_bearer].acquired
    assert not(self.bearers[_bearer].redeemed)
    assert ecrecover(_hash, v, r, s)==_bearer
    self.update_redeem(_bearer, self.bearers[_bearer].distributor)

@public
def give_coupon_to(_target: address):
    assert msg.sender == self.issuer
    assert block.timestamp <=self.end_time
    assert self.remain_coupons > 0
    assert not(self.bearers[_target].acquired)
    self.update_acquire(_target, self.issuer)
