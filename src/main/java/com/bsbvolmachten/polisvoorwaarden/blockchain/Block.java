package com.bsbvolmachten.polisvoorwaarden.blockchain;

import java.util.Date;

public class Block {

    private String blockHash;
    private String prevBlockHash;
    private long timestamp;
    private int index;

    private String documentHash;
    private String documentVersion;
    private String documentCode;
    private String documentTitle;
    private String companyCode;

    public String generateDigest() {
        return "";
    }
}
