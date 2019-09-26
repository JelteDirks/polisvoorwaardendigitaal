package com.bsbvolmachten.polisvoorwaarden;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilsTests {

    @Test
    public void sha256TextEncoding() throws NoSuchAlgorithmException {
        String testString = "123456";

        assertEquals("8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92", Utils.sha256Encode(testString.getBytes()));
    }

    @Test
    public void bytesToHexStringTest() {
        byte[] bytes = {-1, -86, -69};
        String hex = "ffaabb";

        assertEquals(hex, Utils.bytesToHexString(bytes));
    }
}
