package org.crypitor.dash.model;

import org.crypitor.domain.GsonSingleton;

import java.util.ArrayList;

public class Block {
    private String hash;
    private long confirmations;
    private long size;
    private long height;
    private long version;
    private String versionHex;
    private String merkleroot;
    private ArrayList<RawTransaction> tx = new ArrayList<>();
    private CbTx CbTxObject;
    private long time;
    private long mediantime;
    private long nonce;
    private String bits;
    private long difficulty;
    private String chainwork;
    private String previousblockhash;
    private String nextblockhash;
    private boolean chainlock;

    public ArrayList<RawTransaction> getTx() {
        return tx;
    }

    public void setTx(ArrayList<RawTransaction> tx) {
        this.tx = tx;
    }

    public CbTx getCbTxObject() {
        return CbTxObject;
    }

    public void setCbTxObject(CbTx cbTxObject) {
        CbTxObject = cbTxObject;
    }

    public boolean isChainlock() {
        return chainlock;
    }

    public String getHash() {
        return hash;
    }

    public long getConfirmations() {
        return confirmations;
    }

    public long getSize() {
        return size;
    }

    public long getHeight() {
        return height;
    }

    public long getVersion() {
        return version;
    }

    public String getVersionHex() {
        return versionHex;
    }

    public String getMerkleroot() {
        return merkleroot;
    }

    public CbTx getCbTx() {
        return CbTxObject;
    }

    public long getTime() {
        return time;
    }

    public long getMediantime() {
        return mediantime;
    }

    public long getNonce() {
        return nonce;
    }

    public String getBits() {
        return bits;
    }

    public long getDifficulty() {
        return difficulty;
    }

    public String getChainwork() {
        return chainwork;
    }

    public String getPreviousblockhash() {
        return previousblockhash;
    }

    public String getNextblockhash() {
        return nextblockhash;
    }

    public boolean getChainlock() {
        return chainlock;
    }

    // Setter Methods

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setConfirmations(long confirmations) {
        this.confirmations = confirmations;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public void setVersionHex(String versionHex) {
        this.versionHex = versionHex;
    }

    public void setMerkleroot(String merkleroot) {
        this.merkleroot = merkleroot;
    }

    public void setCbTx(CbTx cbTxObject) {
        this.CbTxObject = cbTxObject;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setMediantime(long mediantime) {
        this.mediantime = mediantime;
    }

    public void setNonce(long nonce) {
        this.nonce = nonce;
    }

    public void setBits(String bits) {
        this.bits = bits;
    }

    public void setDifficulty(long difficulty) {
        this.difficulty = difficulty;
    }

    public void setChainwork(String chainwork) {
        this.chainwork = chainwork;
    }

    public void setPreviousblockhash(String previousblockhash) {
        this.previousblockhash = previousblockhash;
    }

    public void setNextblockhash(String nextblockhash) {
        this.nextblockhash = nextblockhash;
    }

    public void setChainlock(boolean chainlock) {
        this.chainlock = chainlock;
    }

    @Override
    public String toString() {
        return GsonSingleton.getInstance().toJson(this);
    }

    public static class RawTransaction {
        private String txid;
        private long size;
        private long version;
        private long type;
        private long locktime;
        private ArrayList<Vin> vin = new ArrayList<Vin>();
        private ArrayList<Vout> vout = new ArrayList<Vout>();
        private boolean instantlock;
        private boolean instantlock_internal;
        private boolean chainlock;

        public ArrayList<Vin> getVin() {
            return vin;
        }

        public void setVin(ArrayList<Vin> vin) {
            this.vin = vin;
        }

        public ArrayList<Vout> getVout() {
            return vout;
        }

        public void setVout(ArrayList<Vout> vout) {
            this.vout = vout;
        }

        public boolean isInstantlock() {
            return instantlock;
        }

        public boolean isInstantlock_internal() {
            return instantlock_internal;
        }

        public boolean isChainlock() {
            return chainlock;
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

        public boolean getInstantlock() {
            return instantlock;
        }

        public boolean getInstantlock_internal() {
            return instantlock_internal;
        }

        public boolean getChainlock() {
            return chainlock;
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

        public void setInstantlock(boolean instantlock) {
            this.instantlock = instantlock;
        }

        public void setInstantlock_internal(boolean instantlock_internal) {
            this.instantlock_internal = instantlock_internal;
        }

        public void setChainlock(boolean chainlock) {
            this.chainlock = chainlock;
        }

    }
}