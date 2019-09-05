package org.crypitor.dash.model;

import java.util.ArrayList;

public class ScriptPubKey {
    private String asm;
    private String hex;
    private long reqSigs;
    private String type;
    private ArrayList< Object > addresses = new ArrayList < Object > ();


    // Getter Methods

    public String getAsm() {
        return asm;
    }

    public String getHex() {
        return hex;
    }

    public long getReqSigs() {
        return reqSigs;
    }

    public String getType() {
        return type;
    }

    // Setter Methods

    public void setAsm(String asm) {
        this.asm = asm;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public void setReqSigs(long reqSigs) {
        this.reqSigs = reqSigs;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Object> getAddresses() {
        return addresses;
    }

    public void setAddresses(ArrayList<Object> addresses) {
        this.addresses = addresses;
    }
}