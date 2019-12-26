# Events

AddDistributor: event({_distributor: address})
RemoveDistributor: event({_distributor: address})
TransferBearer: event({_from: address, _to: address})
Acquire: event({_address: address, _distributor: address})
Redeem: event({_address: address, _distributor: address})
GetSalary: event({_address: address, _amount: uint256(wei)})
Refund: event({_amount: uint256(wei)})

# Functions

@public
def initialize(_issuer: address, _num_coupons: uint256, _wei_per_redeemtion: uint256(wei), _time_limit: uint256(sec), _refund: uint256(wei)):
    pass

@public
def add_distributor(_new_distributor: address) -> bool:
    pass

@public
def remove_distributor(_target_distributor: address) -> bool:
    pass

@public
def transfer(_dest_address: address) -> bool:
    pass

@public
def get_salary():
    pass

@public
def get_refund():
    pass

@public
def acquire(_hash: bytes32, v: uint256, r: uint256, s: uint256, _distributor: address) -> bool:
    pass

@public
def redeem(_bearer: address, _hash: bytes32, v: uint256, r: uint256, s: uint256) -> bool:
    pass
