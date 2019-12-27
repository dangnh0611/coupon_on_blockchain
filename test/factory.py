import json
from web3 import Web3

# Fill in your infura API key here
infura_url = "https://ropsten.infura.io/v3/8f21cada4d3d48db8e74bd7643afad9c"
web3 = Web3(Web3.HTTPProvider(infura_url))

# OMG Address
abi = json.loads('[{"name": "NewCampain", "inputs": [{"type": "address", "name": "_issuer", "indexed": true}, {"type": "bool", "name": "_is_free", "indexed": false}, {"type": "uint256", "name": "_num_coupon", "indexed": false}, {"type": "uint256", "name": "_wei_per_redeemtion", "indexed": false, "unit": "wei"}, {"type": "string", "name": "_name", "indexed": false}, {"type": "string20", "name": "_category", "indexed": true}, {"type": "string", "name": "_description", "indexed": false}], "anonymous": false, "type": "event"}, {"name": "Refund", "inputs": [{"type": "address", "name": "_issuer", "indexed": true}, {"type": "uint256", "name": "_amount", "indexed": false, "unit": "wei"}], "anonymous": false, "type": "event"}, {"outputs": [], "inputs": [{"type": "address", "name": "_campain_template"}], "constant": false, "payable": false, "type": "constructor"}, {"name": "create_campain", "outputs": [], "inputs": [{"type": "uint256", "name": "_num_coupon"}, {"type": "bool", "name": "_is_free"}, {"type": "uint256", "unit": "wei", "name": "_wei_per_redeemtion"}, {"type": "string", "name": "_name"}, {"type": "string", "name": "_category"}, {"type": "string", "name": "_description"}, {"type": "uint256", "unit": "sec", "name": "_time_limit"}], "constant": false, "payable": true, "type": "function", "gas": 225338}, {"name": "campain_template", "outputs": [{"type": "address", "name": "out"}], "inputs": [], "constant": true, "payable": false, "type": "function", "gas": 543}]')
# OMG ABI
address = '0xA421d241bb74A23a01e8fc2C86fedf18dda56Be1'

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

print("Transaction start...")
tx = contract.functions.create_campain(99, False, 1, 'Campain 1', 'book', 'Sale off 50per all book!', 1000000).buildTransaction({'nonce': web3.eth.getTransactionCount(public_key), 'gas': 500000})
# tx['gas']=500000
# tx['gas']=contract.functions.add_distributor('0x855108d766f477f7716636386e4BC2B69c65EFFE').estimateGas()
tx['value']=web3.toWei(0.05, 'ether')
print(tx)
signed_tx = web3.eth.account.signTransaction(tx, private_key=private_key)
tx_hash=web3.eth.sendRawTransaction(signed_tx.rawTransaction)
# tx_hash=contract.functions.add_distributor('0x16FA6349E3fbA711D0cE342674990a6D00aF9B18').transact()
print('tx_hash', tx_hash)
web3.eth.waitForTransactionReceipt(tx_hash)
print('-------------')