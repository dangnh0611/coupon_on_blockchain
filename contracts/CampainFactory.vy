contract Campain:
    def initialize(_issuer: address, _is_free_from_issuer: bool, _num_coupons: uint256, _wei_per_redeemtion: uint256(wei), _end_time: uint256(sec, positional), _name: string[50], _category: string[20], _description: string[100]) : modifying

NewCampain: event({_issuer: indexed(address),_address: address, _is_free_from_issuer: indexed(bool), _num_coupon: uint256, _wei_per_redeemtion: wei_value, _end_time: timestamp, _name: string[50], _category: string[20], _description: string[100] })
Refund: event({_issuer: indexed(address), _amount: wei_value})
Acquire: event({_address: indexed(address), _campain: indexed(address), _end_time: timestamp})
Redeem: event({_address: indexed(address), campain: indexed(address)})

campain_template: address

@public
def __init__(_campain_template: address):
    self.campain_template=_campain_template

@public 
@payable
def create_campain(_num_coupon: uint256,_is_free_from_issuer: bool, _wei_per_redeemtion: wei_value, _name: string[50], _category: string[20], _description: string[100], _end_time: timestamp):
    assert _num_coupon>0
    _required_value: wei_value= _wei_per_redeemtion*_num_coupon
    assert msg.value>= _required_value
    assert block.timestamp < _end_time

    new_campain: address = create_forwarder_to(self.campain_template, value=_required_value)
    Campain(new_campain).initialize(msg.sender, _is_free_from_issuer, _num_coupon, _wei_per_redeemtion, _end_time, _name, _category, _description)
    log.NewCampain(msg.sender, new_campain, _is_free_from_issuer, _num_coupon, _wei_per_redeemtion, _end_time, _name, _category, _description)
    refund: wei_value = msg.value - _required_value
    send(msg.sender, refund)
    log.Refund(msg.sender, refund)

@public
def log_acquire(_bearer: address, _end_time: timestamp):
    log.Acquire(_bearer, msg.sender, _end_time)

@public
def log_redeem(_bearer: address):
    log.Redeem(_bearer, msg.sender)

    

