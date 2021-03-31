import json
from web3 import Web3

# Fill in your infura API key here
infura_url = "https://ropsten.infura.io/v3/8f21cada4d3d48db8e74bd7643afad9c"
web3 = Web3(Web3.HTTPProvider(infura_url))

# OMG Address
abi = json.loads('[{"name":"AddDistributor","inputs":[{"type":"address","name":"_distributor","indexed":false}],"anonymous":false,"type":"event"},{"name":"RemoveDistributor","inputs":[{"type":"address","name":"_distributor","indexed":false}],"anonymous":false,"type":"event"},{"name":"TransferBearer","inputs":[{"type":"address","name":"_from","indexed":true},{"type":"address","name":"_to","indexed":true}],"anonymous":false,"type":"event"},{"name":"Acquire","inputs":[{"type":"address","name":"_address","indexed":true},{"type":"address","name":"_distributor","indexed":true}],"anonymous":false,"type":"event"},{"name":"Redeem","inputs":[{"type":"address","name":"_address","indexed":true},{"type":"address","name":"_distributor","indexed":true}],"anonymous":false,"type":"event"},{"name":"GetSalary","inputs":[{"type":"address","name":"_address","indexed":true},{"type":"uint256","name":"_amount","indexed":false,"unit":"wei"}],"anonymous":false,"type":"event"},{"name":"Refund","inputs":[{"type":"uint256","name":"_amount","indexed":false,"unit":"wei"}],"anonymous":false,"type":"event"},{"outputs":[],"inputs":[{"type":"address","name":"_issuer"},{"type":"bool","name":"_is_free_from_issuer"},{"type":"uint256","name":"_num_coupons"},{"type":"uint256","unit":"wei","name":"_wei_per_redeemtion"},{"type":"uint256","unit":"sec","name":"_time_limit"},{"type":"string","name":"_name"},{"type":"string","name":"_category"},{"type":"string","name":"_description"}],"constant":false,"payable":true,"type":"constructor"},{"name":"initialize","outputs":[],"inputs":[{"type":"address","name":"_issuer"},{"type":"bool","name":"_is_free_from_issuer"},{"type":"uint256","name":"_num_coupons"},{"type":"uint256","unit":"wei","name":"_wei_per_redeemtion"},{"type":"uint256","unit":"sec","name":"_time_limit"},{"type":"string","name":"_name"},{"type":"string","name":"_category"},{"type":"string","name":"_description"}],"constant":false,"payable":true,"type":"function","gas":749125},{"name":"get_distributors_address","outputs":[{"type":"address","name":"out"}],"inputs":[{"type":"uint256","name":"_idx"}],"constant":true,"payable":false,"type":"function","gas":724},{"name":"get_distributors_status","outputs":[{"type":"uint256[2]","name":"out"}],"inputs":[{"type":"uint256","name":"_idx"}],"constant":true,"payable":false,"type":"function","gas":1216},{"name":"get_distributors_withdrawed","outputs":[{"type":"uint256","unit":"wei","name":"out"}],"inputs":[{"type":"uint256","name":"_idx"}],"constant":true,"payable":false,"type":"function","gas":790},{"name":"get_campain_name","outputs":[{"type":"string","name":"out"}],"inputs":[],"constant":true,"payable":false,"type":"function","gas":11539},{"name":"get_campain_category","outputs":[{"type":"string","name":"out"}],"inputs":[],"constant":true,"payable":false,"type":"function","gas":6424},{"name":"get_campain_description","outputs":[{"type":"string","name":"out"}],"inputs":[],"constant":true,"payable":false,"type":"function","gas":21889},{"name":"get_campain_endtime","outputs":[{"type":"uint256","unit":"sec","name":"out"}],"inputs":[],"constant":true,"payable":false,"type":"function","gas":723},{"name":"get_wei_per_redeemed","outputs":[{"type":"uint256","unit":"wei","name":"out"}],"inputs":[],"constant":true,"payable":false,"type":"function","gas":753},{"name":"get_campain_status","outputs":[{"type":"uint256[3]","name":"out"}],"inputs":[],"constant":true,"payable":false,"type":"function","gas":1270},{"name":"add_distributor","outputs":[{"type":"bool","name":"out"}],"inputs":[{"type":"address","name":"_new_distributor"}],"constant":false,"payable":false,"type":"function","gas":147529},{"name":"remove_distributor","outputs":[{"type":"bool","name":"out"}],"inputs":[{"type":"address","name":"_target_distributor"}],"constant":false,"payable":false,"type":"function","gas":4131670},{"name":"transfer_coupon","outputs":[{"type":"bool","name":"out"}],"inputs":[{"type":"address","name":"_dest_address"}],"constant":false,"payable":false,"type":"function","gas":150132},{"name":"withdraw","outputs":[],"inputs":[],"constant":false,"payable":false,"type":"function","gas":3825904},{"name":"final_refund","outputs":[],"inputs":[],"constant":false,"payable":false,"type":"function","gas":38381},{"name":"free_acquire_from_issuer","outputs":[{"type":"bool","name":"out"}],"inputs":[],"constant":false,"payable":false,"type":"function","gas":1978219},{"name":"acquire","outputs":[{"type":"bool","name":"out"}],"inputs":[{"type":"bytes32","name":"_hash"},{"type":"uint256","name":"v"},{"type":"uint256","name":"r"},{"type":"uint256","name":"s"},{"type":"address","name":"_distributor"}],"constant":false,"payable":false,"type":"function","gas":2011112},{"name":"redeem","outputs":[{"type":"bool","name":"out"}],"inputs":[{"type":"address","name":"_bearer"},{"type":"bytes32","name":"_hash"},{"type":"uint256","name":"v"},{"type":"uint256","name":"r"},{"type":"uint256","name":"s"}],"constant":false,"payable":false,"type":"function","gas":1909063}]')
# OMG ABI
address = '0x248B9b1BbE41b964eC7D66f84d29bF19BB483222'

contract = web3.eth.contract(address=address, abi=abi)

# totalSupply = contract.functions.totalSupply().call()
# print(web3.fromWei(totalSupply, 'ether'))
# print(contract.functions.name().call())
# print(contract.functions.symbol().call())
# balance = contract.functions.balanceOf('0xd26114cd6EE289AccF82350c8d8487fedB8A0C07').call()
# print(web3.fromWei(balance, 'ether'))
print("acount", web3.eth.accounts)
# web3.eth.defaultAccount='0x95b45b0BBCF221E0f7Cb16b82Aa2648771Be2536'
public_key='0x95b45b0BBCF221E0f7Cb16b82Aa2648771Be2536'
private_key='0xD7C2467297B410E676DC1C0BF58E1E6EF9892D05290D48C0A71D6F6207CF92FF'
# num_coupons= contract.functions.get_total_coupons().call()
# print(num_coupons)
print(contract.functions.get_campain_name().call())
# print(contract.functions.get_campain_category().call())
# print(contract.functions.get_campain_description().call())
# print(contract.functions.get_distributors(0).call())
# print(contract.functions.get_remain_coupons().call())
# print(contract.functions.get_wei_per_redeemtion().call())
# print(contract.functions.get_num_acquired('0x95b45b0BBCF221E0f7Cb16b82Aa2648771Be2536').call())

print("Transaction start...")
tx = contract.functions.add_distributor('0xf3A566312478c62e05B1958020B3e837C8E21B42').buildTransaction({'nonce': web3.eth.getTransactionCount(public_key)})
tx['gas']=500000
# tx['gas']=contract.functions.add_distributor('0x855108d766f477f7716636386e4BC2B69c65EFFE').estimateGas()
print(tx)
signed_tx = web3.eth.account.signTransaction(tx, private_key=private_key)
tx_hash=web3.eth.sendRawTransaction(signed_tx.rawTransaction)
# tx_hash=contract.functions.add_distributor('0x16FA6349E3fbA711D0cE342674990a6D00aF9B18').transact()
print('tx_hash', tx_hash)

# web3.eth.waitForTransactionReceipt(tx_hash)
# print('-------------')
# for i in range(50):
#     print(i,contract.functions.get_distributors(i).call())