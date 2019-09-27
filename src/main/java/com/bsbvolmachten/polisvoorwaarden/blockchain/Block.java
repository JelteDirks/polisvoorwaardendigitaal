package com.bsbvolmachten.polisvoorwaarden.blockchain;

import com.bsbvolmachten.polisvoorwaarden.Utils;

import java.security.NoSuchAlgorithmException;

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

    public Block(String prevBlockHash,
                 long timestamp,
                 int index,
                 String documentHash,
                 String documentVersion,
                 String documentCode,
                 String documentTitle,
                 String companyCode
    ) {
        this.prevBlockHash = prevBlockHash;
        this.timestamp = timestamp;
        this.index = index;
        this.documentHash = documentHash;
        this.documentVersion = documentVersion;
        this.documentCode = documentCode;
        this.documentTitle = documentTitle;
        this.companyCode = companyCode;
    }

    public String generateDigest() {

        String data = "{" +
                prevBlockHash + "_" +
                index + "_" +
                timestamp + "_" +
                documentHash + "_" +
                documentCode + "_" +
                documentTitle + "_" +
                documentVersion + "_" +
                companyCode + "_" +
                "}";

        try {
            blockHash = Utils.sha256Encode(data.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("error hashing block " + data);
            e.printStackTrace();
        }

        return prevBlockHash;
    }
}
