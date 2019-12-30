package ContractWrapper;

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
import org.web3j.abi.datatypes.Bool;
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
import org.web3j.protocol.core.methods.request.EthFilter;
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
 * <p>Generated with web3j version 4.2.0.
 */
public class Campain extends Contract {
    private static final String BINARY = "0x740100000000000000000000000000000000000000006020526f7fffffffffffffffffffffffffffffff6040527fffffffffffffffffffffffffffffffff8000000000000000000000000000000060605274012a05f1fffffffffffffffffffffffffdabf41c006080527ffffffffffffffffffffffffed5fa0e000000000000000000000000000000000060a05261010061199061014039602061199060c03960c05160205181106100b057600080fd5b50602060206119900160c03960c051600281106100cc57600080fd5b506052602060a06119900160c03960c05161199001610240396032602060a06119900160c03960c05160040135111561010457600080fd5b6034602060c06119900160c03960c051611990016102c0396014602060c06119900160c03960c05160040135111561013b57600080fd5b6084602060e06119900160c03960c05161199001610320396064602060e06119900160c03960c05160040135111561017257600080fd5b60016000556101a05115156101885760006101ae565b610180516101a051610180516101a0510204146101a457600080fd5b610180516101a051025b6008556008543410156101c057600080fd5b61024080600b60c052602060c020602082510161012060006003818352015b826101205160200211156101f257610214565b61012051602002850151610120518501555b81516001018083528114156101df575b5050505050506102c080600c60c052602060c020602082510161012060006002818352015b8261012051602002111561024c5761026e565b61012051602002850151610120518501555b8151600101808352811415610239575b50505050505061032080600d60c052602060c020602082510161012060006005818352015b826101205160200211156102a6576102c8565b61012051602002850151610120518501555b8151600101808352811415610293575b50505050505061016051600e556101c05160075561014051600355610180516004556101a051600255600454600555600960c052602060c02060c052602060c02061014051815560006001820155600060028201556000600382015550600160015561197856600035601c52740100000000000000000000000000000000000000006020526f7fffffffffffffffffffffffffffffff6040527fffffffffffffffffffffffffffffffff8000000000000000000000000000000060605274012a05f1fffffffffffffffffffffffffdabf41c006080527ffffffffffffffffffffffffed5fa0e000000000000000000000000000000000060a05263aff3574960005114156102d35760043560205181106100b257600080fd5b50602435600281106100c357600080fd5b50605260a43560040161014037603260a4356004013511156100e457600080fd5b603460c4356004016101c037601460c43560040135111561010457600080fd5b608460e43560040161022037606460e43560040135111561012457600080fd5b6000541561013157600080fd5b60016000556064351515610146576000610166565b60443560643560443560643502041461015e57600080fd5b604435606435025b60085561014080600b60c052602060c020602082510161012060006003818352015b8261012051602002111561019b576101bd565b61012051602002850151610120518501555b8151600101808352811415610188575b5050505050506101c080600c60c052602060c020602082510161012060006002818352015b826101205160200211156101f557610217565b61012051602002850151610120518501555b81516001018083528114156101e2575b50505050505061022080600d60c052602060c020602082510161012060006005818352015b8261012051602002111561024f57610271565b61012051602002850151610120518501555b815160010180835281141561023c575b505050505050602435600e55608435600755600435600355604435600455606435600255600454600555600960c052602060c02060c052602060c020600435815560006001820155600060028201556000600382015550600160015533600f55005b636567dc4a600051141561031b5734156102ec57600080fd5b600435603281106102fc57600080fd5b600960c052602060c0200160c052602060c0205460005260206000f350005b63d1e9b84b600051141561039657341561033457600080fd5b61016060016004356032811061034957600080fd5b600960c052602060c0200160c052602060c0200154815260026004356032811061037257600080fd5b600960c052602060c0200160c052602060c02001548160200152506040610160f350005b63ee53dd1b60005114156103e15734156103af57600080fd5b6003600435603281106103c157600080fd5b600960c052602060c0200160c052602060c020015460005260206000f350005b632c00638d60005114156104885734156103fa57600080fd5b600435602051811061040b57600080fd5b50610140600a60043560e05260c052604060c0208060c052602060c02054825260018160c052602060c0200154826020015260028160c052602060c0200154826040015260038160c052602060c0200154826060015250506101e06101405181526101605181602001526101805181604001525060606101e0f350005b6399a9fa0d600051141561055a5734156104a157600080fd5b600b8060c052602060c020610180602082540161012060006003818352015b826101205160200211156104d3576104f5565b61012051850154610120516020028501525b81516001018083528114156104c0575b505050505050610200610180516040818352015b604061020051111561051a57610536565b6000610200516101a001535b8151600101808352811415610509575b50506020610160526040610180510160206001820306601f8201039050610160f350005b63ead922f6600051141561062c57341561057357600080fd5b600c8060c052602060c020610180602082540161012060006002818352015b826101205160200211156105a5576105c7565b61012051850154610120516020028501525b8151600101808352811415610592575b5050505050506101e0610180516020818352015b60206101e05111156105ec57610608565b60006101e0516101a001535b81516001018083528114156105db575b50506020610160526040610180510160206001820306601f8201039050610160f350005b635e2e06a660005114156106fe57341561064557600080fd5b600d8060c052602060c020610180602082540161012060006005818352015b8261012051602002111561067757610699565b61012051850154610120516020028501525b8151600101808352811415610664575b505050505050610240610180516080818352015b60806102405111156106be576106da565b6000610240516101a001535b81516001018083528114156106ad575b50506020610160526040610180510160206001820306601f8201039050610160f350005b63f20e39a4600051141561072557341561071757600080fd5b60075460005260206000f350005b636eecf74f600051141561074c57341561073e57600080fd5b60025460005260206000f350005b63b1123681600051141561078757341561076557600080fd5b610160600454815260055481602001526006548160400152506060610160f350005b636d484f02600051141561093b5734156107a057600080fd5b60043560205181106107b157600080fd5b5060035433146107c057600080fd5b6032600154106107cf57600080fd5b7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff6101405261016060006032818352015b600435610160516032811061081457600080fd5b600960c052602060c0200160c052602060c020541861083257600080fd5b610160516032811061084357600080fd5b600960c052602060c0200160c052602060c02054157fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff6101405114161561088d5761016051610140525b5b8151600101808352811415610800575b50507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff6101405118156109395761014051603281106108dc57600080fd5b600960c052602060c0200160c052602060c020600435815560006001820155600060028201556000600382015550600435610180527f3afd547f7eb357741de51229846ae795cb06449e4b4032eb34a7cc0a751e25c16020610180a15b005b63770cec236000511415610a6257341561095457600080fd5b600435602051811061096557600080fd5b50600354331461097457600080fd5b61014060006032818352015b600435610140516032811061099457600080fd5b600960c052602060c0200160c052602060c020541415610a4d57600161014051603281106109c157600080fd5b600960c052602060c0200160c052602060c0200154156109e057600080fd5b61014051603281106109f157600080fd5b600960c052602060c0200160c052602060c0206000815560006001820155600060028201556000600382015550600435610160527f43dd2f8ce4e5873e618ffabcae9856729ea5811dbc5e1c9be4365fcf7b7cf7036020610160a15b5b8151600101808352811415610980575b5050005b633ac442286000511415610bb9573415610a7b57600080fd5b6004356020518110610a8c57600080fd5b50600754421115610a9c57600080fd5b600a60043560e05260c052604060c02060c052602060c0205415610abf57600080fd5b600a3360e05260c052604060c02060c052602060c02054610adf57600080fd5b6001600a3360e05260c052604060c02060c052602060c020015415610b0357600080fd5b6002600a3360e05260c052604060c02060c052602060c020015415610b2757600080fd5b60016001600a3360e05260c052604060c02060c052602060c0200155600a60043560e05260c052604060c02060c052602060c0206001815560006001820155600060028201556003600a3360e05260c052604060c02060c052602060c0200154600382015550600435337fd68f762a17db414b31522c40e5e9dc14fda19c0aebd316223bc821d7cb0e7f1360006000a3005b600015610d2f575b6101805261014052610160526005600181541015610bde57600080fd5b60018154038155506101a060006032818352015b610160516101a05160328110610c0757600080fd5b600960c052602060c0200160c052602060c020541415610c675760016101a05160328110610c3457600080fd5b600960c052602060c0200160c052602060c02001805460018254011015610c5a57600080fd5b6001815401815550610c78565b5b8151600101808352811415610bf2575b5050600a6101405160e05260c052604060c02060c052602060c0206001815560006001820155600060028201556101605160038201555061016051610140517f917b6d640fc6e2a6574871a567f3f7a29df9202d11b163f27c268ced8921fe3560006000a3600f543b610cea57600080fd5b600f543018610cf857600080fd5b60006000604463ed730c226101c052610140516101e052600754610200526101dc6000600f545af1610d2957600080fd5b61018051565b600015610e8b575b6101805261014052610160526006805460018254011015610d5757600080fd5b60018154018155506101a060006032818352015b610160516101a05160328110610d8057600080fd5b600960c052602060c0200160c052602060c020541415610de05760026101a05160328110610dad57600080fd5b600960c052602060c0200160c052602060c02001805460018254011015610dd357600080fd5b6001815401815550610df1565b5b8151600101808352811415610d6b575b505060016002600a6101405160e05260c052604060c02060c052602060c020015561016051610140517f2f39d8fc9160ae00ea6a02229ae376184d7ad48d669c511863fedeb3f6f27edf60006000a3600f543b610e4d57600080fd5b600f543018610e5b57600080fd5b600060006024636e72a3a06101c052610140516101e0526101dc6000600f545af1610e8557600080fd5b61018051565b633ccfd60b60005114156110a2573415610ea457600080fd5b600060025411610eb357600080fd5b61014060006032818352015b336101405160328110610ed157600080fd5b600960c052602060c0200160c052602060c0205414156110895760026101405160328110610efe57600080fd5b600960c052602060c0200160c052602060c02001546101605260006101605111156110885760036101405160328110610f3657600080fd5b600960c052602060c0200160c052602060c02001546002541515610f5b576000610f7e565b6101605160025461016051600254020414610f7557600080fd5b61016051600254025b1015610f8957600080fd5b60036101405160328110610f9c57600080fd5b600960c052602060c0200160c052602060c02001546002541515610fc1576000610fe4565b6101605160025461016051600254020414610fdb57600080fd5b61016051600254025b036101805260006101805111610ff957600080fd5b600060006000600061018051336000f161101257600080fd5b610180516101a052337fad600bfdaa27b1936022a8954698af115bfceff5dab89ff9d99521f8e3d1ab1460206101a0a26003610140516032811061105557600080fd5b600960c052602060c0200160c052602060c02001805461018051825401101561107d57600080fd5b610180518154018155505b5b61109e565b8151600101808352811415610ebf575b5050005b63635cd16860005114156111305734156110bb57600080fd5b60075442116110c957600080fd5b60035433146110d757600080fd5b6000600854116110e657600080fd5b60006000600060006008546003546000f161110057600080fd5b600854610140527f2e1897b0591d764356194f7a795238a87c1987c7a877568e50d829d547c92b976020610140a1005b630f14c78d60005114156111a057341561114957600080fd5b60075442111561115857600080fd5b600354331861116657600080fd5b60006005541161117557600080fd5b634e77c31561014052336101605260035461018052610180516101605160065801610bc1565b600050005b63b99e282a60005114156113a75734156111b957600080fd5b60843560205181106111ca57600080fd5b506007544211156111da57600080fd5b6000600554116111e957600080fd5b60006101405261016060006032818352015b608435610160516032811061120f57600080fd5b600960c052602060c0200160c052602060c02054141561123457600161014052611245565b5b81516001018083528114156111fb575b50506101405161125457600080fd5b600354331861126257600080fd5b6000336020826101800101526020810190503060208261018001015260208101905080610180526101809050805160208201209050600435146112a457600080fd5b600a3360e05260c052604060c02060c052602060c02054156112c557600080fd5b6001600a3360e05260c052604060c02060c052602060c0200154156112e957600080fd5b60843560043561020052602435610220526044356102405260643561026052602060c0608061020060006001610bb8f15060c0511461132757600080fd5b6101406102c0525b6102c0515160206102c051016102c0526102c06102c05110156113515761132f565b634e77c3156102e052336103005260843561032052610320516103005160065801610bc1565b6102a06102c0525b6102c0515260206102c051036102c0526101406102c0511015156113a25761137f565b600050005b63a814c1fe60005114156115755734156113c057600080fd5b60043560205181106113d157600080fd5b506007544211156113e157600080fd5b60035433146113ef57600080fd5b600060043560208261014001015260208101905030602082610140010152602081019050806101405261014090508051602082012090506024351461143357600080fd5b6001600a60043560e05260c052604060c02060c052602060c02001541561145957600080fd5b600a60043560e05260c052604060c02060c052602060c0205461147b57600080fd5b6002600a60043560e05260c052604060c02060c052602060c0200154156114a157600080fd5b6004356024356101c0526044356101e0526064356102005260843561022052602060c060806101c060006001610bb8f15060c051146114df57600080fd5b6101405161016051610180516101a0516101c0516101e051610200516102205161024051610260516357d4f7766102a0526004356102c0526003600a60043560e05260c052604060c02060c052602060c02001546102e0526102e0516102c05160065801610d37565b610260526102405261022052610200526101e0526101c0526101a052610180526101605261014052600050005b63517703de600051141561161c57341561158e57600080fd5b600435602051811061159f57600080fd5b5060035433146115ae57600080fd5b6007544211156115bd57600080fd5b6000600554116115cc57600080fd5b600a60043560e05260c052604060c02060c052602060c02054156115ef57600080fd5b634e77c315610140526004356101605260035461018052610180516101605160065801610bc1565b600050005b63ba92d382600051141561164357341561163557600080fd5b600f5460005260206000f350005b60006000fd5b61032f6119780361032f60003961032f611978036000f3\n";

    public static final String FUNC_INITIALIZE = "initialize";

    public static final String FUNC_GET_DISTRIBUTORS_ADDRESS = "get_distributors_address";

    public static final String FUNC_GET_DISTRIBUTORS_STATUS = "get_distributors_status";

    public static final String FUNC_GET_DISTRIBUTORS_WITHDRAWED = "get_distributors_withdrawed";

    public static final String FUNC_GET_BEARER_STATUS = "get_bearer_status";

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

    public static final String FUNC_GIVE_COUPON_TO = "give_coupon_to";

    public static final String FUNC_FACTORY_ADDRESS = "factory_address";

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

    public RemoteCall<TransactionReceipt> initialize(String _issuer, Boolean _is_free_from_issuer, BigInteger _num_coupons, BigInteger _wei_per_redeemtion, BigInteger _end_time, String _name, String _category, String _description, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INITIALIZE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_issuer), 
                new org.web3j.abi.datatypes.Bool(_is_free_from_issuer), 
                new org.web3j.abi.datatypes.generated.Uint256(_num_coupons), 
                new org.web3j.abi.datatypes.generated.Uint256(_wei_per_redeemtion), 
                new org.web3j.abi.datatypes.generated.Uint256(_end_time), 
                new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_category), 
                new org.web3j.abi.datatypes.Utf8String(_description)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<String> get_distributors_address(BigInteger _idx) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GET_DISTRIBUTORS_ADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_idx)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<List> get_distributors_status(BigInteger _idx) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GET_DISTRIBUTORS_STATUS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_idx)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray2<Uint256>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<BigInteger> get_distributors_withdrawed(BigInteger _idx) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GET_DISTRIBUTORS_WITHDRAWED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_idx)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<List> get_bearer_status(String _address) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GET_BEARER_STATUS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_address)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray3<Bool>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<String> get_campain_name() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GET_CAMPAIN_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> get_campain_category() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GET_CAMPAIN_CATEGORY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> get_campain_description() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GET_CAMPAIN_DESCRIPTION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> get_campain_endtime() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GET_CAMPAIN_ENDTIME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> get_wei_per_redeemed() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GET_WEI_PER_REDEEMED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<List> get_campain_status() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GET_CAMPAIN_STATUS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray3<Uint256>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<TransactionReceipt> add_distributor(String _new_distributor) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADD_DISTRIBUTOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_new_distributor)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> remove_distributor(String _target_distributor) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REMOVE_DISTRIBUTOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_target_distributor)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transfer_coupon(String _dest_address) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFER_COUPON, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_dest_address)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> withdraw() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_WITHDRAW, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> final_refund() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_FINAL_REFUND, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> free_acquire_from_issuer() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_FREE_ACQUIRE_FROM_ISSUER, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> acquire(byte[] _hash, BigInteger v, BigInteger r, BigInteger s, String _distributor) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ACQUIRE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_hash), 
                new org.web3j.abi.datatypes.generated.Uint256(v), 
                new org.web3j.abi.datatypes.generated.Uint256(r), 
                new org.web3j.abi.datatypes.generated.Uint256(s), 
                new org.web3j.abi.datatypes.Address(_distributor)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> redeem(String _bearer, byte[] _hash, BigInteger v, BigInteger r, BigInteger s) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REDEEM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_bearer), 
                new org.web3j.abi.datatypes.generated.Bytes32(_hash), 
                new org.web3j.abi.datatypes.generated.Uint256(v), 
                new org.web3j.abi.datatypes.generated.Uint256(r), 
                new org.web3j.abi.datatypes.generated.Uint256(s)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> give_coupon_to(String _target) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_GIVE_COUPON_TO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_target)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> factory_address() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_FACTORY_ADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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

    public static RemoteCall<Campain> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger initialWeiValue, String _issuer, Boolean _is_free_from_issuer, BigInteger _num_coupons, BigInteger _wei_per_redeemtion, BigInteger _end_time, String _name, String _category, String _description) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_issuer), 
                new org.web3j.abi.datatypes.Bool(_is_free_from_issuer), 
                new org.web3j.abi.datatypes.generated.Uint256(_num_coupons), 
                new org.web3j.abi.datatypes.generated.Uint256(_wei_per_redeemtion), 
                new org.web3j.abi.datatypes.generated.Uint256(_end_time), 
                new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_category), 
                new org.web3j.abi.datatypes.Utf8String(_description)));
        return deployRemoteCall(Campain.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor, initialWeiValue);
    }

    public static RemoteCall<Campain> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger initialWeiValue, String _issuer, Boolean _is_free_from_issuer, BigInteger _num_coupons, BigInteger _wei_per_redeemtion, BigInteger _end_time, String _name, String _category, String _description) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_issuer), 
                new org.web3j.abi.datatypes.Bool(_is_free_from_issuer), 
                new org.web3j.abi.datatypes.generated.Uint256(_num_coupons), 
                new org.web3j.abi.datatypes.generated.Uint256(_wei_per_redeemtion), 
                new org.web3j.abi.datatypes.generated.Uint256(_end_time), 
                new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_category), 
                new org.web3j.abi.datatypes.Utf8String(_description)));
        return deployRemoteCall(Campain.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor, initialWeiValue);
    }

    @Deprecated
    public static RemoteCall<Campain> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, String _issuer, Boolean _is_free_from_issuer, BigInteger _num_coupons, BigInteger _wei_per_redeemtion, BigInteger _end_time, String _name, String _category, String _description) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_issuer), 
                new org.web3j.abi.datatypes.Bool(_is_free_from_issuer), 
                new org.web3j.abi.datatypes.generated.Uint256(_num_coupons), 
                new org.web3j.abi.datatypes.generated.Uint256(_wei_per_redeemtion), 
                new org.web3j.abi.datatypes.generated.Uint256(_end_time), 
                new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_category), 
                new org.web3j.abi.datatypes.Utf8String(_description)));
        return deployRemoteCall(Campain.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    @Deprecated
    public static RemoteCall<Campain> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, String _issuer, Boolean _is_free_from_issuer, BigInteger _num_coupons, BigInteger _wei_per_redeemtion, BigInteger _end_time, String _name, String _category, String _description) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_issuer), 
                new org.web3j.abi.datatypes.Bool(_is_free_from_issuer), 
                new org.web3j.abi.datatypes.generated.Uint256(_num_coupons), 
                new org.web3j.abi.datatypes.generated.Uint256(_wei_per_redeemtion), 
                new org.web3j.abi.datatypes.generated.Uint256(_end_time), 
                new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_category), 
                new org.web3j.abi.datatypes.Utf8String(_description)));
        return deployRemoteCall(Campain.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static class AddDistributorEventResponse {
        public Log log;

        public String _distributor;
    }

    public static class RemoveDistributorEventResponse {
        public Log log;

        public String _distributor;
    }

    public static class TransferBearerEventResponse {
        public Log log;

        public String _from;

        public String _to;
    }

    public static class AcquireEventResponse {
        public Log log;

        public String _address;

        public String _distributor;
    }

    public static class RedeemEventResponse {
        public Log log;

        public String _address;

        public String _distributor;
    }

    public static class GetSalaryEventResponse {
        public Log log;

        public String _address;

        public BigInteger _amount;
    }

    public static class RefundEventResponse {
        public Log log;

        public BigInteger _amount;
    }
}
