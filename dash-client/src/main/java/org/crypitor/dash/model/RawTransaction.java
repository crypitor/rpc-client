package org.crypitor.dash.model;

import org.crypitor.domain.GsonSingleton;

import java.util.ArrayList;

public class RawTransaction {
    private String hex;
    private String txid;
    private long size;
    private long version;
    private long type;
    private long locktime;
    ArrayList<Vin> vin = new ArrayList<Vin>();
    ArrayList<Vout> vout = new ArrayList<Vout>();
    private long extraPayloadSize;
    private String extraPayload;
    private CbTx CbTxObject;
    private String blockhash;
    private long height;
    private long confirmations;
    private long time;
    private long blocktime;
    private boolean instantlock;
    private boolean instantlock_internal;
    private boolean chainlock;


    // Getter Methods

    public String getHex() {
        return hex;
    }

    public String getTxid() {
        return txid;
    }

    public long getSize() {
        return size;
    }

    public long getVersion() {
        return version;
    }

    public long getType() {
        return type;
    }

    public long getLocktime() {
        return locktime;
    }

    public long getExtraPayloadSize() {
        return extraPayloadSize;
    }

    public String getExtraPayload() {
        return extraPayload;
    }

    public CbTx getCbTx() {
        return CbTxObject;
    }

    public String getBlockhash() {
        return blockhash;
    }

    public long getHeight() {
        return height;
    }

    public long getConfirmations() {
        return confirmations;
    }

    public long getTime() {
        return time;
    }

    public long getBlocktime() {
        return blocktime;
    }

    public boolean getInstantlock() {
        return instantlock;
    }

    public boolean getInstantlock_internal() {
        return instantlock_internal;
    }

    public boolean getChainlock() {
        return chainlock;
    }

    // Setter Methods

    public void setHex(String hex) {
        this.hex = hex;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public void setType(long type) {
        this.type = type;
    }

    public void setLocktime(long locktime) {
        this.locktime = locktime;
    }

    public void setExtraPayloadSize(long extraPayloadSize) {
        this.extraPayloadSize = extraPayloadSize;
    }

    public void setExtraPayload(String extraPayload) {
        this.extraPayload = extraPayload;
    }

    public void setCbTx(CbTx cbTxObject) {
        this.CbTxObject = cbTxObject;
    }

    public void setBlockhash(String blockhash) {
        this.blockhash = blockhash;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public void setConfirmations(long confirmations) {
        this.confirmations = confirmations;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setBlocktime(long blocktime) {
        this.blocktime = blocktime;
    }

    public void setInstantlock(boolean instantlock) {
        this.instantlock = instantlock;
    }

    public void setInstantlock_internal(boolean instantlock_internal) {
        this.instantlock_internal = instantlock_internal;
    }

    public void setChainlock(boolean chainlock) {
        this.chainlock = chainlock;
    }

    @Override
    public String toString() {
        return GsonSingleton.getInstance().toJson(this);
    }
}