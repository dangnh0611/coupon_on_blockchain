#! /usr/bin/sh

vyper contracts/Campain.vy > compile/Campain.bin
vyper -f abi contracts/Campain.vy > compile/Campain.abi

vyper contracts/CampainFactory.vy > compile/CampainFactory.bin
vyper -f abi contracts/CampainFactory.vy > compile/CampainFactory.abi

~/Downloads/web3j-4.2.0/bin/web3j solidity generate -a=compile/Campain.abi -b=compile/Campain.bin -o=web3j_wrapper -p=ContractWrapper
~/Downloads/web3j-4.2.0/bin/web3j solidity generate -a=compile/CampainFactory.abi -b=compile/CampainFactory.bin -o=web3j_wrapper -p=ContractWrapper
