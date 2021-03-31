package ContractWrapper;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
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
public class CampainFactory extends Contract {
    private static final String BINARY = "0x740100000000000000000000000000000000000000006020526f7fffffffffffffffffffffffffffffff6040527fffffffffffffffffffffffffffffffff8000000000000000000000000000000060605274012a05f1fffffffffffffffffffffffffdabf41c006080527ffffffffffffffffffffffffed5fa0e000000000000000000000000000000000060a052602061074b6101403934156100a157600080fd5b602061074b60c03960c05160205181106100ba57600080fd5b506101405160005561073356600035601c52740100000000000000000000000000000000000000006020526f7fffffffffffffffffffffffffffffff6040527fffffffffffffffffffffffffffffffff8000000000000000000000000000000060605274012a05f1fffffffffffffffffffffffffdabf41c006080527ffffffffffffffffffffffffed5fa0e000000000000000000000000000000000060a05263438256ee60005114156105b057602435600281106100b157600080fd5b5060526064356004016101403760326064356004013511156100d257600080fd5b60346084356004016101c03760146084356004013511156100f257600080fd5b608460a43560040161022037606460a43560040135111561011257600080fd5b60006004351161012157600080fd5b6044351515610131576000610151565b60043560443560043560443502041461014957600080fd5b600435604435025b6102e0526102e05134101561016557600080fd5b60c435421061017357600080fd5b7f6033600c60003960336000f33660006000376110006000366000730000000000610320526c010000000000000000000000006000540261033b527f5af4602c57600080fd5b6110006000f30000000000000000000000000000000061034f5260606103206102e051f0806101e757600080fd5b61030052610300513b6101f957600080fd5b61030051301861020857600080fd5b6000600061024461010063aff357496103c052336103e05260243561040052600435610420526044356104405260c43561046052806104805261014080805160200180846103e001828460006004600a8704601201f161026757600080fd5b50508051820160206001820306601f8201039050602001915050806104a0526101c080805160200180846103e001828460006004600a8704601201f16102ac57600080fd5b50508051820160206001820306601f8201039050602001915050806104c05261022080805160200180846103e001828460006004600a8704601201f16102f157600080fd5b50508051820160206001820306601f82010390506020019150506103dc90506000610300515af161032157600080fd5b610300516106c0526004356106e0526044356107005260c4356107205260e0610680526106805161074052610140805160200180610680516106c001828460006004600a8704601201f161037457600080fd5b5050610660610680516106c00151610220818352015b61022061066051111561039c576103bd565b600061066051610680516106e00101535b815160010180835281141561038a575b50506020610680516106c0015160206001820306601f82010390506106805101016106805261068051610760526101c0805160200180610680516106c001828460006004600a8704601201f161041257600080fd5b5050610660610680516106c00151610220818352015b61022061066051111561043a5761045b565b600061066051610680516106e00101535b8151600101808352811415610428575b50506020610680516106c0015160206001820306601f8201039050610680510101610680526106805161078052610220805160200180610680516106c001828460006004600a8704601201f16104b057600080fd5b5050610660610680516106c00151610220818352015b6102206106605111156104d8576104f9565b600061066051610680516106e00101535b81516001018083528114156104c6575b50506020610680516106c0015160206001820306601f820103905061068051010161068052602435337f6e636e2d48c629bff90dcf679b3b3a1ddd639fab3f0c51abca17c3409238aaee610680516106c0a36102e05134101561055b57600080fd5b6102e05134036107a05260006000600060006107a051336000f161057e57600080fd5b6107a0516107c052337fbb28353e4598c3b9199101a66e0989549b659a59a54d2c27fbb183f1932c8e6d60206107c0a2005b63ed730c22600051141561060f5734156105c957600080fd5b60043560205181106105da57600080fd5b5060243561014052336004357ffecf59f8f14ddf7ed62de270b1df287c7984f089b9508da62f0cacca54ae27ae6020610140a3005b636e72a3a0600051141561066657341561062857600080fd5b600435602051811061063957600080fd5b50336004357f2f39d8fc9160ae00ea6a02229ae376184d7ad48d669c511863fedeb3f6f27edf60006000a3005b60006000fd5b6100c7610733036100c76000396100c7610733036000f3\n";

    public static final String FUNC_CREATE_CAMPAIN = "create_campain";

    public static final String FUNC_LOG_ACQUIRE = "log_acquire";

    public static final String FUNC_LOG_REDEEM = "log_redeem";

    public static final Event NEWCAMPAIN_EVENT = new Event("NewCampain", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>() {}, new TypeReference<Bool>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event REFUND_EVENT = new Event("Refund", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event ACQUIRE_EVENT = new Event("Acquire", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event REDEEM_EVENT = new Event("Redeem", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    @Deprecated
    protected CampainFactory(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CampainFactory(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected CampainFactory(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected CampainFactory(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<NewCampainEventResponse> getNewCampainEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NEWCAMPAIN_EVENT, transactionReceipt);
        ArrayList<NewCampainEventResponse> responses = new ArrayList<NewCampainEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NewCampainEventResponse typedResponse = new NewCampainEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._issuer = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._is_free_from_issuer = (Boolean) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._address = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._num_coupon = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._wei_per_redeemtion = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse._end_time = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse._name = (String) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse._category = (String) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse._description = (String) eventValues.getNonIndexedValues().get(6).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<NewCampainEventResponse> newCampainEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, NewCampainEventResponse>() {
            @Override
            public NewCampainEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NEWCAMPAIN_EVENT, log);
                NewCampainEventResponse typedResponse = new NewCampainEventResponse();
                typedResponse.log = log;
                typedResponse._issuer = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._is_free_from_issuer = (Boolean) eventValues.getIndexedValues().get(1).getValue();
                typedResponse._address = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._num_coupon = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._wei_per_redeemtion = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse._end_time = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse._name = (String) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse._category = (String) eventValues.getNonIndexedValues().get(5).getValue();
                typedResponse._description = (String) eventValues.getNonIndexedValues().get(6).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<NewCampainEventResponse> newCampainEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWCAMPAIN_EVENT));
        return newCampainEventFlowable(filter);
    }

    public List<RefundEventResponse> getRefundEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(REFUND_EVENT, transactionReceipt);
        ArrayList<RefundEventResponse> responses = new ArrayList<RefundEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RefundEventResponse typedResponse = new RefundEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._issuer = (String) eventValues.getIndexedValues().get(0).getValue();
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
                typedResponse._issuer = (String) eventValues.getIndexedValues().get(0).getValue();
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

    public List<AcquireEventResponse> getAcquireEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ACQUIRE_EVENT, transactionReceipt);
        ArrayList<AcquireEventResponse> responses = new ArrayList<AcquireEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AcquireEventResponse typedResponse = new AcquireEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._address = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._campain = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._end_time = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
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
                typedResponse._campain = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse._end_time = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
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
            typedResponse.campain = (String) eventValues.getIndexedValues().get(1).getValue();
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
                typedResponse.campain = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RedeemEventResponse> redeemEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REDEEM_EVENT));
        return redeemEventFlowable(filter);
    }

    public RemoteCall<TransactionReceipt> create_campain(BigInteger _num_coupon, Boolean _is_free_from_issuer, BigInteger _wei_per_redeemtion, String _name, String _category, String _description, BigInteger _end_time, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CREATE_CAMPAIN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_num_coupon), 
                new org.web3j.abi.datatypes.Bool(_is_free_from_issuer), 
                new org.web3j.abi.datatypes.generated.Uint256(_wei_per_redeemtion), 
                new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_category), 
                new org.web3j.abi.datatypes.Utf8String(_description), 
                new org.web3j.abi.datatypes.generated.Uint256(_end_time)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> log_acquire(String _bearer, BigInteger _end_time) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_LOG_ACQUIRE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_bearer), 
                new org.web3j.abi.datatypes.generated.Uint256(_end_time)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> log_redeem(String _bearer) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_LOG_REDEEM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_bearer)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static CampainFactory load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CampainFactory(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static CampainFactory load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CampainFactory(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static CampainFactory load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new CampainFactory(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static CampainFactory load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new CampainFactory(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<CampainFactory> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _campain_template) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_campain_template)));
        return deployRemoteCall(CampainFactory.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<CampainFactory> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _campain_template) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_campain_template)));
        return deployRemoteCall(CampainFactory.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<CampainFactory> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _campain_template) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_campain_template)));
        return deployRemoteCall(CampainFactory.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<CampainFactory> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _campain_template) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_campain_template)));
        return deployRemoteCall(CampainFactory.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class NewCampainEventResponse {
        public Log log;

        public String _issuer;

        public Boolean _is_free_from_issuer;

        public String _address;

        public BigInteger _num_coupon;

        public BigInteger _wei_per_redeemtion;

        public BigInteger _end_time;

        public String _name;

        public String _category;

        public String _description;
    }

    public static class RefundEventResponse {
        public Log log;

        public String _issuer;

        public BigInteger _amount;
    }

    public static class AcquireEventResponse {
        public Log log;

        public String _address;

        public String _campain;

        public BigInteger _end_time;
    }

    public static class RedeemEventResponse {
        public Log log;

        public String _address;

        public String campain;
    }
}
