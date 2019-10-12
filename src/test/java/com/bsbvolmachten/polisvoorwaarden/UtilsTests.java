package com.bsbvolmachten.polisvoorwaarden;

import jdk.jshell.execution.Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilsTests {

    @Test
    public void sha256TextEncoding() throws NoSuchAlgorithmException {
        String testString = "123456";

        assertEquals("8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92",
                Utils.sha256Encode(testString.getBytes()));
    }

    @Test
    public void bytesToHexStringTest() {
        byte[] bytes = {-1, -86, -69};
        String hex = "ffaabb";

        assertEquals(hex, Utils.bytesToHexString(bytes));
    }

    @Test
    public void sha256PDFEncryptionTest() throws IOException, NoSuchAlgorithmException {
        String filePath = "./src/test/resources/Model CF2019 - Combi Autopolis.pdf";

        Path pdfPath = Paths.get(filePath);

        byte[] pdfBytes = Files.readAllBytes(pdfPath);

        assertEquals("4d7fdf861d57149786a9cf5dab770998950dd44d299dc295e8af045904437e09",
                Utils.sha256Encode(pdfBytes));

    }

    @Test
    public void retrievingSecretTest() throws IOException {
        assertEquals("327235753878214125442A472D4B6150645367566B597033733676397924423F4528" +
                        "482B4D6251655468576D5A7134743777217A25432A46294A404E635266556A586E3272357538782F41" +
                        "3F4428472B4B6150645367566B5970337336763979244226452948404D6351655468576D5A71347437" +
                        "77217A25432A462D4A614E645267556A586E3272357538782F413F4428472B4B6250655368566D5970" +
                        "337336763979244226452948404D635166546A576E5A7234743777217A25432A462D4A614E64526755" +
                        "6B58703273357638782F413F4428472B4B6250655368566D597133743677397A244226452948404D63" +
                        "5166546A576E5A7234753778214125442A462D4A614E645267556B58703273357638792F423F452848" +
                        "2B4B6250655368566D597133743677397A24432646294A404E635166546A576E5A7234753778214125" +
                        "442A472D4B6150645367556B58703273357638792F423F4528482B4D6251655468576D597133743677" +
                        "397A24432646294A404E635266556A586E327234753778214125442A472D4B6150645367566B597033" +
                        "73367638792F423F4528482B4D6251655468576D5A7134743777217A24432646294A404E635266556A" +
                        "586E3272357538782F413F442A472D4B6150645367566B5970337336763979244226452948404D6251" +
                        "655468576D5A7134743777217A25432A462D4A614E645266556A58",
                Utils.getSecret(new File("./src/test/resources/testSecret.txt")));
    }

    @Test
    public void validateJWSTest() throws IOException {
        String secret = Utils.getSecret(new File("./src/test/resources/testSecret.txt"));

        String jws = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.exzei3YCERPZ204FgXKjmh4QHpI3bzDBVIkqjWyLcbw";

        Utils.validateJWS(jws, secret);
    }
}
