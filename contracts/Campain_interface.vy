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
def initialize(_issuer: address, _is_free_from_issuer: bool, _num_coupons: uint256, _wei_per_redeemtion: uint256(wei), _end_time: uint256(sec, positional), _name: string[50], _category: string[20], _description: string[100]):
    pass

@constant
@public
def get_distributors_address(_idx: uint256) -> address:
    pass

@constant
@public
def get_distributors_status(_idx: uint256) -> uint256[2]:
    pass

@constant
@public
def get_distributors_withdrawed(_idx: uint256) -> uint256(wei):
    pass

@constant
@public
def get_campain_name() -> string[50]:
    pass

@constant
@public
def get_campain_category() -> string[20]:
    pass

@constant
@public
def get_campain_description() -> string[100]:
    pass

@constant
@public
def get_campain_endtime() -> uint256(sec, positional):
    pass

@constant
@public
def get_wei_per_redeemed() -> uint256(wei):
    pass

@constant
@public
def get_campain_status() -> uint256[3]:
    pass

@public
def add_distributor(_new_distributor: address):
    pass

@public
def remove_distributor(_target_distributor: address):
    pass

@public
def transfer_coupon(_dest_address: address):
    pass

@public
def withdraw():
    pass

@public
def final_refund():
    pass

@public
def free_acquire_from_issuer():
    pass

@public
def acquire(_hash: bytes32, v: uint256, r: uint256, s: uint256, _distributor: address):
    pass

@public
def redeem(_bearer: address, _hash: bytes32, v: uint256, r: uint256, s: uint256):
    pass


