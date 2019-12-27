import Campain_interface as Campain

NewCampain: event({_issuer: indexed(address), _is_free_from_issuer: bool, _num_coupon: uint256, _wei_per_redeemtion: wei_value, _name: string[50], _category: indexed(string[20]), _description: string[100] })
Refund: event({_issuer: indexed(address), _amount: wei_value})

campain_template: public(address)

@public
def __init__(_campain_template: address):
    self.campain_template=_campain_template

@public 
@payable
def create_campain(_num_coupon: uint256,_is_free_from_issuer: bool, _wei_per_redeemtion: wei_value, _name: string[50], _category: string[20], _description: string[100], _time_limit: timedelta):
    assert _num_coupon>0
    _required_value: wei_value= _wei_per_redeemtion*_num_coupon
    assert msg.value>= _required_value

    new_campain: address = create_forwarder_to(self.campain_template, value=_required_value)
    Campain(new_campain).initialize(msg.sender, _is_free_from_issuer, _num_coupon, _wei_per_redeemtion, _time_limit, _name, _category, _description)
    log.NewCampain(msg.sender,_is_free_from_issuer, _num_coupon, _wei_per_redeemtion, _name, _category, _description)
    refund: wei_value = msg.value - _required_value
    send(msg.sender, refund)
    log.Refund(msg.sender, refund)

    

