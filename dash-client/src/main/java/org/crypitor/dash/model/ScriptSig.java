package org.crypitor.dash.model;

public class ScriptSig {
    private String asm;
    private String hex;


    // Getter Methods

    public String getAsm() {
        return asm;
    }

    public String getHex() {
        return hex;
    }

    // Setter Methods

    public void setAsm(String asm) {
        this.asm = asm;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }
}
