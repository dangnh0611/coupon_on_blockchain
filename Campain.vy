struct Bearer:
    acquired: bool
    transfered: bool
    redeemed: bool
    distributor: address

struct Distributor:
    alive: bool
    num_acquired: uint256
    num_redeemed: uint256

AddDistributor: event({_distributor: address})
RemoveDistributor: event({_distributor: address})
TransferBearer: event({_from: indexed(address), _to: indexed(address)})
Acquire: event({_address: indexed(address), _distributor: indexed(address)})
Redeem: event({_address: indexed(address), _distributor: indexed(address)})
GetSalary: event({_address: address, _amount: wei_value})

wei_per_redeemtion: wei_value
issuer: address
num_coupons: uint256
remain_coupons: uint256
num_redeem: uint256
end_time: timestamp
distributors: map(address, Distributor)
bearers: map(address, Bearer)

@public
def __init__(_issuer: address, _num_coupons: uint256, _wei_per_redeemtion: wei_value, _time_limit: timedelta):
    self.end_time= block.timestamp + _time_limit
    self.issuer= _issuer
    self.num_coupons=_num_coupons
    self.wei_per_redeemtion= _wei_per_redeemtion
    self.remain_coupons= self.num_coupons
    self.distributors[_issuer]=Distributor({alive: True, num_acquired: 0, num_redeemed: 0})

@public
def add_distributor(_new_distributor: address) -> bool:
    if self.distributors[_new_distributor].alive==True:
        return False
    else:
        self.distributors[_new_distributor]=Distributor({alive: True, num_acquired: 0, num_redeemed: 0})
        log.AddDistributor(_new_distributor)
        return True

@public
def remove_distributor(_target_distributor: address) -> bool:
    if self.distributors[_target_distributor].alive==False:
        return False
    else:
        clear(self.distributors[_target_distributor])
        log.RemoveDistributor(_target_distributor)
        return True

@public 
def transfer(_dest_address: address) -> bool:
    if block.timestamp > self.end_time:
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
    self.distributors[_distributor].num_acquired+=1
    self.bearers[_bearer]=Bearer({acquired: True, transfered: False, redeemed: False, distributor: _distributor})
    log.Acquire(_bearer, _distributor)
    return True

@private
def update_redeem(_bearer: address, _distributor: address) -> bool:
    self.num_redeem+=1
    self.distributors[_distributor].num_redeemed+=1
    self.bearers[_bearer].redeemed=True
    log.Redeem(_bearer, _distributor)
    return True

@public
def get_salary():
    assert self.distributors[msg.sender].alive
    num_redeemed: uint256 = self.distributors[msg.sender].num_redeemed
    if num_redeemed>0:
        salary: wei_value=self.wei_per_redeemtion*num_redeemed
        send(msg.sender, salary)
        log.GetSalary(msg.sender, salary)

@public
def acquire(_hash: bytes32, v: uint256, r: uint256, s: uint256, _distributor: address) -> bool:
    if (block.timestamp > self.end_time):
        return False
    if self.remain_coupons==0:
        return False
    if not self.distributors[_distributor].alive:
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
