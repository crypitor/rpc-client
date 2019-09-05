package org.crypitor.dash.model;

public class Vin {
    private String txid;
    private long vout;
    private ScriptSig scriptSig;
    private long sequence;


    // Getter Methods

    public String getTxid() {
        return txid;
    }

    public long getVout() {
        return vout;
    }

    public ScriptSig getScriptSig() {
        return scriptSig;
    }

    public long getSequence() {
        return sequence;
    }

    // Setter Methods

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public void setVout(long vout) {
        this.vout = vout;
    }

    public void setScriptSig(ScriptSig scriptSigObject) {
        this.scriptSig = scriptSigObject;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }
}

