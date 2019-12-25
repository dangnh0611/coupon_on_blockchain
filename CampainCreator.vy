import Campain_interface as Campain

NewCampain: event({_issuer: indexed(address), _num_coupon: uint256, _wei_per_redeemtion: wei_value, _name: string[50], _category: indexed(string[20]), _description: string[100] })

@public 
@payable
def create_campain(_issuer: address, _num_coupon: uint256, _wei_per_redeemtion: wei_value, _name: string[50], _category: string[20], _description: string[100], _time_limit: timedelta):
    assert _num_coupon>0
    assert _wei_per_redeemtion>0
    required_value: wei_value= _wei_per_redeemtion*_num_coupon
    assert msg.value>= required_value
    new_campain: address = Campain(msg.sender, _num_coupon, _wei_per_redeemtion, _time_limit)
    refund: wei_value=msg.value-required_value
    

    