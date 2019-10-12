package com.bsbvolmachten.polisvoorwaarden;

import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

    public static String bytesToHexString(byte[] bytes) {

        StringBuilder builder = new StringBuilder();

        for (byte aByte : bytes) {
            builder.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }

        return builder.toString();
    }

    public static String sha256Encode(byte[] bytes) throws NoSuchAlgorithmException {

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

        byte[] hashAsBytes = messageDigest.digest(bytes);

        return bytesToHexString(hashAsBytes);
    }

    public static String getSecret(File secretFile) throws IOException {
        StringBuilder secret = new StringBuilder();
        FileReader reader = new FileReader(secretFile);
        BufferedReader bReader = new BufferedReader(reader);

        String s;
        while ((s = bReader.readLine()) != null) {
            secret.append(s);
        }

        return secret.toString();
    }

    public static io.jsonwebtoken.Jws validateJWS(String jwsString, String secret) {

        Jws<Claims> jws;

        Key key = new SecretKeySpec(secret.getBytes(), 0, secret.getBytes().length, "HmacSHA512");


        try {
            jws = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwsString);

            return jws;

        } catch (JwtException jwtEx) {
            jwtEx.printStackTrace();
        }

        return null;
    }
}
