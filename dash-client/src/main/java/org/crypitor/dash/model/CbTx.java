package org.crypitor.dash.model;

public class CbTx {
    private long version;
    private long height;
    private String merkleRootMNList;
    private String merkleRootQuorums;


    // Getter Methods

    public long getVersion() {
        return version;
    }

    public long getHeight() {
        return height;
    }

    public String getMerkleRootMNList() {
        return merkleRootMNList;
    }

    public String getMerkleRootQuorums() {
        return merkleRootQuorums;
    }

    // Setter Methods

    public void setVersion(long version) {
        this.version = version;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public void setMerkleRootMNList(String merkleRootMNList) {
        this.merkleRootMNList = merkleRootMNList;
    }

    public void setMerkleRootQuorums(String merkleRootQuorums) {
        this.merkleRootQuorums = merkleRootQuorums;
    }
}