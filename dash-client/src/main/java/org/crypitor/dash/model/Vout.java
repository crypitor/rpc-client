package org.crypitor.dash.model;

public class Vout {
    private String value;
    private long valueSat;
    private int n;
    private ScriptPubKey scriptPubKey;


    // Getter Methods

    public String getValue() {
        return value;
    }

    public long getValueSat() {
        return valueSat;
    }

    public int getN() {
        return n;
    }

    public ScriptPubKey getScriptPubKey() {
        return scriptPubKey;
    }

    // Setter Methods

    public void setValue(String value) {
        this.value = value;
    }

    public void setValueSat(long valueSat) {
        this.valueSat = valueSat;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setScriptPubKey(ScriptPubKey scriptPubKeyObject) {
        this.scriptPubKey = scriptPubKeyObject;
    }

}
