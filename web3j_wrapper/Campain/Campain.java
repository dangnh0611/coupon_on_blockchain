package Campain;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.StaticArray2;
import org.web3j.abi.datatypes.generated.StaticArray3;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.11.
 */
@SuppressWarnings("rawtypes")
public class Campain extends Contract {
    public static final String BINARY = "0x740100000000000000000000000000000000000000006020526f7fffffffffffffffffffffffffffffff6040527fffffffffffffffffffffffffffffffff8000000000000000000000000000000060605274012a05f1fffffffffffffffffffffffffdabf41c006080527ffffffffffffffffffffffffed5fa0e000000000000000000000000000000000060a0526101006118af6101403960206118af60c03960c05160205181106100b057600080fd5b50602060206118af0160c03960c051600281106100cc57600080fd5b506052602060a06118af0160c03960c0516118af01610240396032602060a06118af0160c03960c05160040135111561010457600080fd5b6034602060c06118af0160c03960c0516118af016102c0396014602060c06118af0160c03960c05160040135111561013b57600080fd5b6084602060e06118af0160c03960c0516118af01610320396064602060e06118af0160c03960c05160040135111561017257600080fd5b60016000556101a05115156101885760006101ae565b610180516101a051610180516101a0510204146101a457600080fd5b610180516101a051025b6008556008543410156101c057600080fd5b61024080600b60c052602060c020602082510161012060006003818352015b826101205160200211156101f257610214565b61012051602002850151610120518501555b81516001018083528114156101df575b5050505050506102c080600c60c052602060c020602082510161012060006002818352015b8261012051602002111561024c5761026e565b61012051602002850151610120518501555b8151600101808352811415610239575b50505050505061032080600d60c052602060c020602082510161012060006005818352015b826101205160200211156102a6576102c8565b61012051602002850151610120518501555b8151600101808352811415610293575b50505050505061016051600e55426101c051420110156102e757600080fd5b6101c051420160075561014051600355610180516004556101a051600255600454600555600960c052602060c02060c052602060c02061014051815560006001820155600060028201556000600382015550600060015561189756600035601c52740100000000000000000000000000000000000000006020526f7fffffffffffffffffffffffffffffff6040527fffffffffffffffffffffffffffffffff8000000000000000000000000000000060605274012a05f1fffffffffffffffffffffffffdabf41c006080527ffffffffffffffffffffffffed5fa0e000000000000000000000000000000000060a05263aff3574960005114156102e25760043560205181106100b257600080fd5b50602435600281106100c357600080fd5b50605260a43560040161014037603260a4356004013511156100e457600080fd5b603460c4356004016101c037601460c43560040135111561010457600080fd5b608460e43560040161022037606460e43560040135111561012457600080fd5b6000541561013157600080fd5b60016000556064351515610146576000610166565b60443560643560443560643502041461015e57600080fd5b604435606435025b60085561014080600b60c052602060c020602082510161012060006003818352015b8261012051602002111561019b576101bd565b61012051602002850151610120518501555b8151600101808352811415610188575b5050505050506101c080600c60c052602060c020602082510161012060006002818352015b826101205160200211156101f557610217565b61012051602002850151610120518501555b81516001018083528114156101e2575b50505050505061022080600d60c052602060c020602082510161012060006005818352015b8261012051602002111561024f57610271565b61012051602002850151610120518501555b815160010180835281141561023c575b505050505050602435600e55426084354201101561028e57600080fd5b6084354201600755600435600355604435600455606435600255600454600555600960c052602060c02060c052602060c0206004358155600060018201556000600282015560006003820155506000600155005b636567dc4a600051141561032a5734156102fb57600080fd5b6004356032811061030b57600080fd5b600960c052602060c0200160c052602060c0205460005260206000f350005b63d1e9b84b60005114156103a557341561034357600080fd5b61016060016004356032811061035857600080fd5b600960c052602060c0200160c052602060c0200154815260026004356032811061038157600080fd5b600960c052602060c0200160c052602060c02001548160200152506040610160f350005b63ee53dd1b60005114156103f05734156103be57600080fd5b6003600435603281106103d057600080fd5b600960c052602060c0200160c052602060c020015460005260206000f350005b6399a9fa0d60005114156104c257341561040957600080fd5b600b8060c052602060c020610180602082540161012060006003818352015b8261012051602002111561043b5761045d565b61012051850154610120516020028501525b8151600101808352811415610428575b505050505050610200610180516040818352015b60406102005111156104825761049e565b6000610200516101a001535b8151600101808352811415610471575b50506020610160526040610180510160206001820306601f8201039050610160f350005b63ead922f660005114156105945734156104db57600080fd5b600c8060c052602060c020610180602082540161012060006002818352015b8261012051602002111561050d5761052f565b61012051850154610120516020028501525b81516001018083528114156104fa575b5050505050506101e0610180516020818352015b60206101e051111561055457610570565b60006101e0516101a001535b8151600101808352811415610543575b50506020610160526040610180510160206001820306601f8201039050610160f350005b635e2e06a660005114156106665734156105ad57600080fd5b600d8060c052602060c020610180602082540161012060006005818352015b826101205160200211156105df57610601565b61012051850154610120516020028501525b81516001018083528114156105cc575b505050505050610240610180516080818352015b608061024051111561062657610642565b6000610240516101a001535b8151600101808352811415610615575b50506020610160526040610180510160206001820306601f8201039050610160f350005b63f20e39a4600051141561068d57341561067f57600080fd5b60075460005260206000f350005b636eecf74f60005114156106b45734156106a657600080fd5b60025460005260206000f350005b63b112368160005114156106ef5734156106cd57600080fd5b610160600454815260055481602001526006548160400152506060610160f350005b636d484f0260005114156108db57341561070857600080fd5b600435602051811061071957600080fd5b5060035433181561072f57600060005260206000f35b6032600154141561074557600060005260206000f35b7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff6101405261016060006032818352015b610160516032811061078757600080fd5b600960c052602060c0200160c052602060c02054157fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff610140511416156107d5576101605161014052610818565b60043561016051603281106107e957600080fd5b600960c052602060c0200160c052602060c02054141561081257600060005260206000f3610817565b610819565b5b5b8151600101808352811415610776575b50507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff6101405118156108ce57610140516032811061086757600080fd5b600960c052602060c0200160c052602060c020600435815560006001820155600060028201556000600382015550600435610180527f3afd547f7eb357741de51229846ae795cb06449e4b4032eb34a7cc0a751e25c16020610180a1600160005260206000f35b600060005260206000f350005b63770cec236000511415610a2c5734156108f457600080fd5b600435602051811061090557600080fd5b5060035433181561091b57600060005260206000f35b61014060006032818352015b600435610140516032811061093b57600080fd5b600960c052602060c0200160c052602060c020541415610a0c5760006001610140516032811061096a57600080fd5b600960c052602060c0200160c052602060c0200154181561099457600060005260206000f3610a0b565b61014051603281106109a557600080fd5b600960c052602060c0200160c052602060c0206000815560006001820155600060028201556000600382015550600435610160527f43dd2f8ce4e5873e618ffabcae9856729ea5811dbc5e1c9be4365fcf7b7cf7036020610160a1600160005260206000f35b5b5b8151600101808352811415610927575b5050600060005260206000f350005b633ac442286000511415610b9b573415610a4557600080fd5b6004356020518110610a5657600080fd5b50600754421115610a6c57600060005260206000f35b6001600a60043560e05260c052604060c02060c052602060c020541415610a9857600060005260206000f35b6001600a3360e05260c052604060c02060c052602060c0200154156001600a3360e05260c052604060c02060c052602060c0205414166002600a3360e05260c052604060c02060c052602060c0200154151615610b8e5760016001600a3360e05260c052604060c02060c052602060c0200155600a60043560e05260c052604060c02060c052602060c0206001815560006001820155600060028201556003600a3360e05260c052604060c02060c052602060c0200154600382015550600435337fd68f762a17db414b31522c40e5e9dc14fda19c0aebd316223bc821d7cb0e7f1360006000a3600160005260206000f3610b99565b600060005260206000f35b005b600015610ccf575b6101805261014052610160526005600181541015610bc057600080fd5b60018154038155506101a060006032818352015b610160516101a05160328110610be957600080fd5b600960c052602060c0200160c052602060c020541415610c495760016101a05160328110610c1657600080fd5b600960c052602060c0200160c052602060c02001805460018254011015610c3c57600080fd5b6001815401815550610c5a565b5b8151600101808352811415610bd4575b5050600a6101405160e05260c052604060c02060c052602060c0206001815560006001820155600060028201556101605160038201555061016051610140517f917b6d640fc6e2a6574871a567f3f7a29df9202d11b163f27c268ced8921fe3560006000a36001600052600051610180515650005b600015610df0575b6101805261014052610160526006805460018254011015610cf757600080fd5b60018154018155506101a060006032818352015b610160516101a05160328110610d2057600080fd5b600960c052602060c0200160c052602060c020541415610d805760026101a05160328110610d4d57600080fd5b600960c052602060c0200160c052602060c02001805460018254011015610d7357600080fd5b6001815401815550610d91565b5b8151600101808352811415610d0b575b505060016002600a6101405160e05260c052604060c02060c052602060c020015561016051610140517f2f39d8fc9160ae00ea6a02229ae376184d7ad48d669c511863fedeb3f6f27edf60006000a36001600052600051610180515650005b633ccfd60b6000511415611007573415610e0957600080fd5b600060025411610e1857600080fd5b61014060006032818352015b336101405160328110610e3657600080fd5b600960c052602060c0200160c052602060c020541415610fee5760026101405160328110610e6357600080fd5b600960c052602060c0200160c052602060c0200154610160526000610160511115610fed5760036101405160328110610e9b57600080fd5b600960c052602060c0200160c052602060c02001546002541515610ec0576000610ee3565b6101605160025461016051600254020414610eda57600080fd5b61016051600254025b1015610eee57600080fd5b60036101405160328110610f0157600080fd5b600960c052602060c0200160c052602060c02001546002541515610f26576000610f49565b6101605160025461016051600254020414610f4057600080fd5b61016051600254025b036101805260006101805111610f5e57600080fd5b600060006000600061018051336000f1610f7757600080fd5b610180516101a052337fad600bfdaa27b1936022a8954698af115bfceff5dab89ff9d99521f8e3d1ab1460206101a0a260036101405160328110610fba57600080fd5b600960c052602060c0200160c052602060c020018054610180518254011015610fe257600080fd5b610180518154018155505b5b611003565b8151600101808352811415610e24575b5050005b63635cd168600051141561109557341561102057600080fd5b600754421161102e57600080fd5b600354331461103c57600080fd5b60006008541161104b57600080fd5b60006000600060006008546003546000f161106557600080fd5b600854610140527f2e1897b0591d764356194f7a795238a87c1987c7a877568e50d829d547c92b976020610140a1005b630f14c78d60005114156111255734156110ae57600080fd5b6007544211156110c357600060005260206000f35b6003543314156110d857600060005260206000f35b60055415156110ec57600060005260206000f35b634e77c31561014052336101605260035461018052610180516101605160065801610ba3565b6101e0526101e05160005260206000f350005b63b99e282a600051141561136557341561113e57600080fd5b608435602051811061114f57600080fd5b5060075442111561116557600060005260206000f35b600554151561117957600060005260206000f35b60006101405261016060006032818352015b608435610160516032811061119f57600080fd5b600960c052602060c0200160c052602060c0205414156111c4576001610140526111d5565b5b815160010180835281141561118b575b50506101405115156111ec57600060005260206000f35b60035433141561120157600060005260206000f35b6000336020826101800101526020810190503060208261018001015260208101905080610180526101809050805160208201209050600435181561124a57600060005260206000f35b60016001600a3360e05260c052604060c02060c052602060c0200154146001600a3360e05260c052604060c02060c052602060c0205414171561129257600060005260206000f35b60843560043561020052602435610220526044356102405260643561026052602060c0608061020060006001610bb8f15060c0511415611358576101406102c0525b6102c0515160206102c051016102c0526102c06102c05110156112f6576112d4565b634e77c3156102e052336103005260843561032052610320516103005160065801610ba3565b610380526102a06102c0525b6102c0515260206102c051036102c0526101406102c05110151561134b57611328565b6103805160005260206000f35b600060005260206000f350005b63a814c1fe600051141561154e57341561137e57600080fd5b600435602051811061138f57600080fd5b506007544211156113a557600060005260206000f35b60035460043514156113bc57600060005260206000f35b60006004356020826101400101526020810190503060208261014001015260208101905080610140526101409050805160208201209050602435181561140757600060005260206000f35b6001600a60043560e05260c052604060c02060c052602060c02054146001600a60043560e05260c052604060c02060c052602060c020015415166002600a60043560e05260c052604060c02060c052602060c02001541516600435602435610280526044356102a0526064356102c0526084356102e052602060c0608061028060006001610bb8f15060c051141615611541576101c0610340525b610340515160206103405101610340526103406103405110156114c4576114a2565b6357d4f77661036052600435610380526003600a60043560e05260c052604060c02060c052602060c02001546103a0526103a0516103805160065801610cd7565b61040052610320610340525b610340515260206103405103610340526101c06103405110151561153457611511565b6104005160005260206000f35b600060005260206000f350005b60006000fd5b61034361189703610343600039610343611897036000f3\n";

    public static final String FUNC_INITIALIZE = "initialize";

    public static final String FUNC_GET_DISTRIBUTORS_ADDRESS = "get_distributors_address";

    public static final String FUNC_GET_DISTRIBUTORS_STATUS = "get_distributors_status";

    public static final String FUNC_GET_DISTRIBUTORS_WITHDRAWED = "get_distributors_withdrawed";

    public static final String FUNC_GET_CAMPAIN_NAME = "get_campain_name";

    public static final String FUNC_GET_CAMPAIN_CATEGORY = "get_campain_category";

    public static final String FUNC_GET_CAMPAIN_DESCRIPTION = "get_campain_description";

    public static final String FUNC_GET_CAMPAIN_ENDTIME = "get_campain_endtime";

    public static final String FUNC_GET_WEI_PER_REDEEMED = "get_wei_per_redeemed";

    public static final String FUNC_GET_CAMPAIN_STATUS = "get_campain_status";

    public static final String FUNC_ADD_DISTRIBUTOR = "add_distributor";

    public static final String FUNC_REMOVE_DISTRIBUTOR = "remove_distributor";

    public static final String FUNC_TRANSFER_COUPON = "transfer_coupon";

    public static final String FUNC_WITHDRAW = "withdraw";

    public static final String FUNC_FINAL_REFUND = "final_refund";

    public static final String FUNC_FREE_ACQUIRE_FROM_ISSUER = "free_acquire_from_issuer";

    public static final String FUNC_ACQUIRE = "acquire";

    public static final String FUNC_REDEEM = "redeem";

    public static final Event ADDDISTRIBUTOR_EVENT = new Event("AddDistributor", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final Event REMOVEDISTRIBUTOR_EVENT = new Event("RemoveDistributor", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final Event TRANSFERBEARER_EVENT = new Event("TransferBearer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event ACQUIRE_EVENT = new Event("Acquire", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event REDEEM_EVENT = new Event("Redeem", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event GETSALARY_EVENT = new Event("GetSalary", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event REFUND_EVENT = new Event("Refund", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected Campain(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Campain(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Campain(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Campain(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<AddDistributorEventResponse> getAddDistributorEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ADDDISTRIBUTOR_EVENT, transactionReceipt);
        ArrayList<AddDistributorEventResponse> responses = new ArrayList<AddDistributorEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AddDistributorEventResponse typedResponse = new AddDistributorEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._distributor = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AddDistributorEventResponse> addDistributorEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, AddDistributorEventResponse>() {
            @Override
            public AddDistributorEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ADDDISTRIBUTOR_EVENT, log);
                AddDistributorEventResponse typedResponse = new AddDistributorEventResponse();
                typedResponse.log = log;
                typedResponse._distributor = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AddDistributorEventResponse> addDistributorEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ADDDISTRIBUTOR_EVENT));
        return addDistributorEventFlowable(filter);
    }

    public List<RemoveDistributorEventResponse> getRemoveDistributorEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(REMOVEDISTRIBUTOR_EVENT, transactionReceipt);
        ArrayList<RemoveDistributorEventResponse> responses = new ArrayList<RemoveDistributorEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RemoveDistributorEventResponse typedResponse = new RemoveDistributorEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._distributor = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RemoveDistributorEventResponse> removeDistributorEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, RemoveDistributorEventResponse>() {
            @Override
            public RemoveDistributorEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(REMOVEDISTRIBUTOR_EVENT, log);
                RemoveDistributorEventResponse typedResponse = new RemoveDistributorEventResponse();
                typedResponse.log = log;
                typedResponse._distributor = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RemoveDistributorEventResponse> removeDistributorEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REMOVEDISTRIBUTOR_EVENT));
        return removeDistributorEventFlowable(filter);
    }

    public List<TransferBearerEventResponse> getTransferBearerEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFERBEARER_EVENT, transactionReceipt);
        ArrayList<TransferBearerEventResponse> responses = new ArrayList<TransferBearerEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferBearerEventResponse typedResponse = new TransferBearerEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._to = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferBearerEventResponse> transferBearerEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferBearerEventResponse>() {
            @Override
            public TransferBearerEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFERBEARER_EVENT, log);
                TransferBearerEventResponse typedResponse = new TransferBearerEventResponse();
                typedResponse.log = log;
                typedResponse._from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._to = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferBearerEventResponse> transferBearerEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFERBEARER_EVENT));
        return transferBearerEventFlowable(filter);
    }

    public List<AcquireEventResponse> getAcquireEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ACQUIRE_EVENT, transactionReceipt);
        ArrayList<AcquireEventResponse> responses = new ArrayList<AcquireEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AcquireEventResponse typedResponse = new AcquireEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._address = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._distributor = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AcquireEventResponse> acquireEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, AcquireEventResponse>() {
            @Override
            public AcquireEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ACQUIRE_EVENT, log);
                AcquireEventResponse typedResponse = new AcquireEventResponse();
                typedResponse.log = log;
                typedResponse._address = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._distributor = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AcquireEventResponse> acquireEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ACQUIRE_EVENT));
        return acquireEventFlowable(filter);
    }

    public List<RedeemEventResponse> getRedeemEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(REDEEM_EVENT, transactionReceipt);
        ArrayList<RedeemEventResponse> responses = new ArrayList<RedeemEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RedeemEventResponse typedResponse = new RedeemEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._address = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._distributor = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RedeemEventResponse> redeemEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, RedeemEventResponse>() {
            @Override
            public RedeemEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(REDEEM_EVENT, log);
                RedeemEventResponse typedResponse = new RedeemEventResponse();
                typedResponse.log = log;
                typedResponse._address = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._distributor = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RedeemEventResponse> redeemEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REDEEM_EVENT));
        return redeemEventFlowable(filter);
    }

    public List<GetSalaryEventResponse> getGetSalaryEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(GETSALARY_EVENT, transactionReceipt);
        ArrayList<GetSalaryEventResponse> responses = new ArrayList<GetSalaryEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            GetSalaryEventResponse typedResponse = new GetSalaryEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._address = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<GetSalaryEventResponse> getSalaryEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, GetSalaryEventResponse>() {
            @Override
            public GetSalaryEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(GETSALARY_EVENT, log);
                GetSalaryEventResponse typedResponse = new GetSalaryEventResponse();
                typedResponse.log = log;
                typedResponse._address = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<GetSalaryEventResponse> getSalaryEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(GETSALARY_EVENT));
        return getSalaryEventFlowable(filter);
    }

    public List<RefundEventResponse> getRefundEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(REFUND_EVENT, transactionReceipt);
        ArrayList<RefundEventResponse> responses = new ArrayList<RefundEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RefundEventResponse typedResponse = new RefundEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RefundEventResponse> refundEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, RefundEventResponse>() {
            @Override
            public RefundEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(REFUND_EVENT, log);
                RefundEventResponse typedResponse = new RefundEventResponse();
                typedResponse.log = log;
                typedResponse._amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RefundEventResponse> refundEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REFUND_EVENT));
        return refundEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> initialize(String _issuer, Boolean _is_free_from_issuer, BigInteger _num_coupons, BigInteger _wei_per_redeemtion, BigInteger _time_limit, String _name, String _category, String _description, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INITIALIZE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _issuer), 
                new org.web3j.abi.datatypes.Bool(_is_free_from_issuer), 
                new org.web3j.abi.datatypes.generated.Uint256(_num_coupons), 
                new org.web3j.abi.datatypes.generated.Uint256(_wei_per_redeemtion), 
                new org.web3j.abi.datatypes.generated.Uint256(_time_limit), 
                new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_category), 
                new org.web3j.abi.datatypes.Utf8String(_description)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<String> get_distributors_address(BigInteger _idx) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GET_DISTRIBUTORS_ADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_idx)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<List> get_distributors_status(BigInteger _idx) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GET_DISTRIBUTORS_STATUS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_idx)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray2<Uint256>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> get_distributors_withdrawed(BigInteger _idx) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GET_DISTRIBUTORS_WITHDRAWED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_idx)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> get_campain_name() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GET_CAMPAIN_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> get_campain_category() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GET_CAMPAIN_CATEGORY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> get_campain_description() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GET_CAMPAIN_DESCRIPTION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> get_campain_endtime() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GET_CAMPAIN_ENDTIME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> get_wei_per_redeemed() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GET_WEI_PER_REDEEMED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<List> get_campain_status() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GET_CAMPAIN_STATUS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray3<Uint256>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> add_distributor(String _new_distributor) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADD_DISTRIBUTOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _new_distributor)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> remove_distributor(String _target_distributor) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REMOVE_DISTRIBUTOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _target_distributor)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer_coupon(String _dest_address) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFER_COUPON, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _dest_address)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> withdraw() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_WITHDRAW, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> final_refund() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_FINAL_REFUND, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> free_acquire_from_issuer() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_FREE_ACQUIRE_FROM_ISSUER, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> acquire(byte[] _hash, BigInteger v, BigInteger r, BigInteger s, String _distributor) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ACQUIRE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_hash), 
                new org.web3j.abi.datatypes.generated.Uint256(v), 
                new org.web3j.abi.datatypes.generated.Uint256(r), 
                new org.web3j.abi.datatypes.generated.Uint256(s), 
                new org.web3j.abi.datatypes.Address(160, _distributor)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> redeem(String _bearer, byte[] _hash, BigInteger v, BigInteger r, BigInteger s) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REDEEM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _bearer), 
                new org.web3j.abi.datatypes.generated.Bytes32(_hash), 
                new org.web3j.abi.datatypes.generated.Uint256(v), 
                new org.web3j.abi.datatypes.generated.Uint256(r), 
                new org.web3j.abi.datatypes.generated.Uint256(s)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Campain load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Campain(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Campain load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Campain(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Campain load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Campain(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Campain load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Campain(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Campain> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger initialWeiValue, String _issuer, Boolean _is_free_from_issuer, BigInteger _num_coupons, BigInteger _wei_per_redeemtion, BigInteger _time_limit, String _name, String _category, String _description) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _issuer), 
                new org.web3j.abi.datatypes.Bool(_is_free_from_issuer), 
                new org.web3j.abi.datatypes.generated.Uint256(_num_coupons), 
                new org.web3j.abi.datatypes.generated.Uint256(_wei_per_redeemtion), 
                new org.web3j.abi.datatypes.generated.Uint256(_time_limit), 
                new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_category), 
                new org.web3j.abi.datatypes.Utf8String(_description)));
        return deployRemoteCall(Campain.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor, initialWeiValue);
    }

    public static RemoteCall<Campain> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger initialWeiValue, String _issuer, Boolean _is_free_from_issuer, BigInteger _num_coupons, BigInteger _wei_per_redeemtion, BigInteger _time_limit, String _name, String _category, String _description) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _issuer), 
                new org.web3j.abi.datatypes.Bool(_is_free_from_issuer), 
                new org.web3j.abi.datatypes.generated.Uint256(_num_coupons), 
                new org.web3j.abi.datatypes.generated.Uint256(_wei_per_redeemtion), 
                new org.web3j.abi.datatypes.generated.Uint256(_time_limit), 
                new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_category), 
                new org.web3j.abi.datatypes.Utf8String(_description)));
        return deployRemoteCall(Campain.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor, initialWeiValue);
    }

    @Deprecated
    public static RemoteCall<Campain> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, String _issuer, Boolean _is_free_from_issuer, BigInteger _num_coupons, BigInteger _wei_per_redeemtion, BigInteger _time_limit, String _name, String _category, String _description) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _issuer), 
                new org.web3j.abi.datatypes.Bool(_is_free_from_issuer), 
                new org.web3j.abi.datatypes.generated.Uint256(_num_coupons), 
                new org.web3j.abi.datatypes.generated.Uint256(_wei_per_redeemtion), 
                new org.web3j.abi.datatypes.generated.Uint256(_time_limit), 
                new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_category), 
                new org.web3j.abi.datatypes.Utf8String(_description)));
        return deployRemoteCall(Campain.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    @Deprecated
    public static RemoteCall<Campain> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, String _issuer, Boolean _is_free_from_issuer, BigInteger _num_coupons, BigInteger _wei_per_redeemtion, BigInteger _time_limit, String _name, String _category, String _description) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _issuer), 
                new org.web3j.abi.datatypes.Bool(_is_free_from_issuer), 
                new org.web3j.abi.datatypes.generated.Uint256(_num_coupons), 
                new org.web3j.abi.datatypes.generated.Uint256(_wei_per_redeemtion), 
                new org.web3j.abi.datatypes.generated.Uint256(_time_limit), 
                new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_category), 
                new org.web3j.abi.datatypes.Utf8String(_description)));
        return deployRemoteCall(Campain.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static class AddDistributorEventResponse extends BaseEventResponse {
        public String _distributor;
    }

    public static class RemoveDistributorEventResponse extends BaseEventResponse {
        public String _distributor;
    }

    public static class TransferBearerEventResponse extends BaseEventResponse {
        public String _from;

        public String _to;
    }

    public static class AcquireEventResponse extends BaseEventResponse {
        public String _address;

        public String _distributor;
    }

    public static class RedeemEventResponse extends BaseEventResponse {
        public String _address;

        public String _distributor;
    }

    public static class GetSalaryEventResponse extends BaseEventResponse {
        public String _address;

        public BigInteger _amount;
    }

    public static class RefundEventResponse extends BaseEventResponse {
        public BigInteger _amount;
    }
}
