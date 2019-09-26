package com.bsbvolmachten.polisvoorwaarden;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

    public static String bytesToHexString(byte[] bytes) {

        StringBuffer builder = new StringBuffer();

        for (int i = 0; i < bytes.length; i++) {
            builder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        return builder.toString();
    }

    public static String sha256Encode(byte[] bytes) throws NoSuchAlgorithmException {

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

        byte[] hashAsBytes = messageDigest.digest(bytes);

        return bytesToHexString(hashAsBytes);
    }

    public static String sha256PDFEncryption(File file) throws IOException {

        if (!file.canRead()) {
            throw new IOException("can not read file: " + file.getAbsolutePath());
        }

        return "";
    }
}
