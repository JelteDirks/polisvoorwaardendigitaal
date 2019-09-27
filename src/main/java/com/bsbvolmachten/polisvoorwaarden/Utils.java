package com.bsbvolmachten.polisvoorwaarden;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class Utils {

    static String bytesToHexString(byte[] bytes) {

        StringBuilder builder = new StringBuilder();

        for (byte aByte : bytes) {
            builder.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }

        return builder.toString();
    }

    static String sha256Encode(byte[] bytes) throws NoSuchAlgorithmException {

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

        byte[] hashAsBytes = messageDigest.digest(bytes);

        return bytesToHexString(hashAsBytes);
    }
}
