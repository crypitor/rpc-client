package org.crypitor.dash.rpc;

import com.google.gson.JsonElement;
import org.crypitor.dash.model.Block;
import org.crypitor.dash.model.RawTransaction;
import org.crypitor.domain.GsonSingleton;
import org.crypitor.rpc.RpcHandler;
import org.crypitor.rpc.RpcRequest;
import org.crypitor.rpc.RpcRequestBuilder;
import org.crypitor.rpc.RpcResponse;
import org.crypitor.rpc.exception.RpcCommunicateException;
import org.crypitor.rpc.exception.RpcHttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DashRpcClient {

    private static final Logger logger = LoggerFactory.getLogger(DashRpcClient.class);

    private static String node_url = "https://localhost:9998";
    private static String user = "username";
    private static String password = "password";
    private static String passphrase = "passphrase";
    private static int walletUnlockTimeout = 10;

    private static RpcRequestBuilder rpcRequestBuilder = RpcRequestBuilder.getBuilder()
            .setUrl(node_url).setUser(user).setPassword(password);

    public void config(String node_url, String user, String password, String passphrase, int walletUnlockTimeout) {
        DashRpcClient.node_url = node_url;
        DashRpcClient.user = user;
        DashRpcClient.password = password;
        DashRpcClient.passphrase = passphrase;
        DashRpcClient.walletUnlockTimeout = walletUnlockTimeout;
        rpcRequestBuilder = RpcRequestBuilder.getBuilder()
                .setUrl(node_url).setUser(user).setPassword(password);
    }

    private static RpcResponse callRequest(String method, List<Object> params) throws RpcCommunicateException, RpcHttpException {
        RpcRequest request = rpcRequestBuilder
                .setMethod(method)
                .setParams(params)
                .build();
        return RpcHandler.performRequest(request);
    }

    /**
     * #########################################################################
     * ## GET DATA #############################################################
     * #########################################################################
     */

    /**
     * get highest block from network
     *
     * @return highest block
     * @throws RpcHttpException for error response, check dash Error for more information
     */
    public static Long getBlockCount() throws RpcCommunicateException, RpcHttpException {
        RpcResponse response = callRequest(DashRpcMethod.getblockcount.name(), Collections.emptyList());
        JsonElement result = GsonSingleton.getInstance().toJsonTree(response.getResult());
        Long blockCount = GsonSingleton.getInstance().fromJson(result, Long.class);
        return blockCount;
    }

    /**
     * get block hash from block number
     *
     * @param blockNumber block height
     * @return hash of block height
     * @throws RpcHttpException for error response, check dash Error for more information
     */
    public static String getBlockHash(long blockNumber) throws RpcCommunicateException, RpcHttpException {
        RpcResponse response = callRequest(DashRpcMethod.getblockhash.name(), Collections.singletonList(blockNumber));
        JsonElement result = GsonSingleton.getInstance().toJsonTree(response.getResult());
        String blockHash = GsonSingleton.getInstance().fromJson(result, String.class);
        return blockHash;
    }

    /**
     * get block detail by blockhash
     *
     * @param blockhash hash of block
     * @param verbosity 0 block will be serialized with hex format
     *                  1 block will return by json object and list transaction hash
     *                  2 block will return by json object and list transaction detail
     * @return block detail follow by verbosity
     * @throws RpcHttpException for error response, check dash Error for more information
     */
    public static Block getBlock(String blockhash, int verbosity) throws RpcCommunicateException, RpcHttpException {
        RpcResponse response = callRequest(DashRpcMethod.getblock.name(), Arrays.asList(blockhash, verbosity));
        JsonElement result = GsonSingleton.getInstance().toJsonTree(response.getResult());
        Block block = GsonSingleton.getInstance().fromJson(result, Block.class);
        return block;
    }

    /**
     * get full transaction detail include vin vout
     *
     * @param transactionHash transaction hash to get detail
     * @param verbosity       0 is hex format
     *                        1 and 2 is json detail
     * @return raw transaction detail
     * @throws RpcHttpException for error response, check dash Error for more information
     */
    public static RawTransaction getrawtransaction(String transactionHash, int verbosity) throws RpcCommunicateException, RpcHttpException {
        RpcResponse response = callRequest(DashRpcMethod.getrawtransaction.name(), Arrays.asList(transactionHash, verbosity));
        JsonElement result = GsonSingleton.getInstance().toJsonTree(response.getResult());
        RawTransaction rawTransaction = GsonSingleton.getInstance().fromJson(result, RawTransaction.class);
        return rawTransaction;
    }

    /**
     * #########################################################################
     * ## WALLET RPC ###########################################################
     * #########################################################################
     */

    /**
     * unlock wallet by passphrase
     *
     * @param passphrase password to unlock
     * @param timeout    timeout seconds
     * @return null on success
     * @throws RpcHttpException may throw Already unlock with error code -17
     */
    public static boolean walletPassphrase(String passphrase, long timeout) throws RpcCommunicateException, RpcHttpException {
        try {
            callRequest(DashRpcMethod.walletpassphrase.name(), Arrays.asList(passphrase, timeout));
            return true;
        } catch (RpcHttpException e) {
            if (e.getError().getCode() == Errors.RPC_WALLET_ALREADY_UNLOCKED.getErrorcode()) {
                logger.info("wallet already unlock");
                return true;
            }
            logger.error("error occur: " + e.getMessage());
            throw e;
        }
    }

    /**
     * lock wallet
     *
     * @return null on success
     * @throws RpcHttpException for error response, check dash Error for more information
     */
    public static Object walletLock() throws RpcCommunicateException, RpcHttpException {
        RpcResponse response = callRequest(DashRpcMethod.walletlock.name(), Collections.emptyList());
        return response;
    }

    public static Object keypoolRefill() throws RpcCommunicateException, RpcHttpException {
        try {
            RpcResponse response = callRequest(DashRpcMethod.keypoolrefill.name(), Collections.emptyList());
            return response;
        } catch (RpcHttpException e) {
            if (e.getError().getCode() == Errors.RPC_WALLET_UNLOCK_NEEDED.getErrorcode()) {
                logger.info("key pool refill need to unlock wallet first");
                walletPassphrase(passphrase, walletUnlockTimeout);
                return keypoolRefill();
            }
            logger.error("can not refill key pool, error occur: " + e.getMessage());
            throw e;
        }
    }

    /**
     * #########################################################################
     * ## ACCOUNT AND ADDRESS ##################################################
     * #########################################################################
     */

    /**
     * get new address
     *
     * @param account get new address for account
     * @return new address
     * @throws RpcHttpException for error response, check dash Error for more information
     */
    public static Object getNewAddress(String account) throws RpcCommunicateException, RpcHttpException {
        try {
            RpcResponse response = callRequest(DashRpcMethod.getnewaddress.name(), Collections.singletonList(account));
            System.out.println(response.toString());
            return response;
        } catch (RpcHttpException e) {
            if (e.getError().getCode() == Errors.RPC_WALLET_KEYPOOL_RAN_OUT.getErrorcode()) {
                logger.info("keypool was ran out, need to refill");
                keypoolRefill();
                return getNewAddress(account);
            }
            logger.error("get new address can not complete because error: " + e.getMessage());
            throw e;
        }
    }

    public static String getAddressByAccount(String account) throws Exception {
        RpcResponse response = callRequest(DashRpcMethod.getaddressesbyaccount.name(), Collections.singletonList(account));
        JsonElement result = GsonSingleton.getInstance().toJsonTree(response.getResult());
        String newAddress = GsonSingleton.getInstance().fromJson(result, String.class);
        return newAddress;
    }

    /**
     * get list account
     *
     * @return map from account name to balance string
     * @throws RpcCommunicateException
     * @throws RpcHttpException
     */
    @SuppressWarnings("unchecked")
    public static HashMap<String, String> listAccounts() throws RpcCommunicateException, RpcHttpException {
        RpcResponse response = callRequest(DashRpcMethod.listaccounts.name(), Collections.emptyList());
        JsonElement result = GsonSingleton.getInstance().toJsonTree(response.getResult());
        HashMap<String, String> accounts = (HashMap<String, String>) GsonSingleton.getInstance().fromJson(result, HashMap.class);
        return accounts;
    }

    /**
     * #########################################################################
     * ## CREATE TRANSACTION ###################################################
     * #########################################################################
     */

    /**
     * send dash to address
     *
     * @param address        receiver
     * @param amount         amount to send with decimal number, for ex: 1.00000000 DASH
     * @param comment        A locally-stored (not broadcast) comment assigned to this transaction. Default is no comment
     * @param comment_to     A locally-stored (not broadcast) comment assigned to this transaction. Meant to be used for describing who the payment was sent to. Default is no comment
     * @param isSubstraction The fee will be deducted from the amount being sent. The recipient will receive less dash than you enter in the amount field. Default is false
     * @param instantSend    If set to true, send this transaction as InstantSend (default: false).
     * @param privateSend    If set to true, use anonymized funds only (default: false).
     * @return transaction id
     * @throws RpcCommunicateException on failed connection
     * @throws RpcHttpException        on rpc return error
     */
    public static String sendToAddress(String address, String amount,
                                       String comment, String comment_to,
                                       boolean isSubstraction, boolean instantSend, boolean privateSend)
            throws RpcCommunicateException, RpcHttpException {
        RpcResponse response = callRequest(DashRpcMethod.sendtoaddress.name(),
                Arrays.asList(address, amount, comment, comment_to, isSubstraction, instantSend, privateSend));
        JsonElement result = GsonSingleton.getInstance().toJsonTree(response.getResult());
        String transactionId = GsonSingleton.getInstance().fromJson(result, String.class);
        return transactionId;
    }

    /**
     * send dash to address
     *
     * @param address receiver
     * @param amount  amount to send with decimal number, for ex: 1.00000000 DASH
     * @return transaction id
     * @throws RpcCommunicateException on failed connection
     * @throws RpcHttpException        on rpc return error
     */
    public static String simpleSendToAddress(String address, String amount)
            throws RpcCommunicateException, RpcHttpException {
        RpcResponse response = callRequest(DashRpcMethod.sendtoaddress.name(),
                Arrays.asList(address, amount, "default", "default receiver comment", false, false, false));
        JsonElement result = GsonSingleton.getInstance().toJsonTree(response.getResult());
        String transactionId = GsonSingleton.getInstance().fromJson(result, String.class);
        return transactionId;
    }

    /**
     * send dash to address
     *
     * @param address    receiver
     * @param amount     amount to send with decimal number, for ex: 1.00000000 DASH
     * @param comment    A locally-stored (not broadcast) comment assigned to this transaction. Default is no comment
     * @param comment_to A locally-stored (not broadcast) comment assigned to this transaction. Meant to be used for describing who the payment was sent to. Default is no comment
     * @return transaction id
     * @throws RpcCommunicateException on failed connection
     * @throws RpcHttpException        on rpc return error
     */
    public static String sendToAddressWithComment(String address, String amount,
                                                  String comment, String comment_to)
            throws RpcCommunicateException, RpcHttpException {
        RpcResponse response = callRequest(DashRpcMethod.sendtoaddress.name(),
                Arrays.asList(address, amount, comment, comment_to, false, false, false));
        JsonElement result = GsonSingleton.getInstance().toJsonTree(response.getResult());
        String transactionId = GsonSingleton.getInstance().fromJson(result, String.class);
        return transactionId;
    }
}
