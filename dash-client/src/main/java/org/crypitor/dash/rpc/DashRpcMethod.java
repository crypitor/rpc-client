package org.crypitor.dash.rpc;

public enum DashRpcMethod {

    /**
     * GETTER METHOD
     */
    getblock,
    gettransaction,
    getblockcount,
    getblockhash,
    getbalance,
    getrawtransaction,
    getwalletinfo,

    /**
     * WALLET METHOD
     */
    dumphdinfo,
    walletpassphrase,
    encryptwallet,
    walletlock,
    keypoolrefill,

    /**
     * ACCOUNT AND ADDRESS METHOD
     */
    getnewaddress,
    getaddressesbyaccount,
    listaccounts,
    listaddressbalances,
    listreceivedbyaddress,

    /**
     * TRANSACTION
     */
    sendtoaddress,
    sendmany,

}
