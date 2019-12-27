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
def initialize(_issuer: address, _is_free_from_issuer: bool, _num_coupons: uint256, _wei_per_redeemtion: uint256(wei), _time_limit: uint256(sec), _name: string[50], _category: string[20], _description: string[100]):
    pass

# @constant
# @public
# def get_distributors_detail(_idx: uint256) -> struct Distributor: {_address: address, num_acquired: uint256, num_redeemed: uint256, received: uint256(wei)}:
#     pass

# @constant
# @public
# def get_campain_detail() -> struct CampainDetail: {name: string[50], category: string[20], description: string[100], end_time: uint256(sec, positional), total: uint256, remain: uint256, num_redeemed: uint256, wei_per_redeemtion: uint256(wei)}:
#     pass

@public
def add_distributor(_new_distributor: address) -> bool:
    pass

@public
def remove_distributor(_target_distributor: address) -> bool:
    pass

@public
def transfer_coupon(_dest_address: address) -> bool:
    pass

@public
def withdraw():
    pass

@public
def final_refund():
    pass

@public
def free_acquire_from_issuer() -> bool:
    pass

@public
def acquire(_hash: bytes32, v: uint256, r: uint256, s: uint256, _distributor: address) -> bool:
    pass

@public
def redeem(_bearer: address, _hash: bytes32, v: uint256, r: uint256, s: uint256) -> bool:
    pass