package com.bsbvolmachten.polisvoorwaarden.blockchain;

import com.bsbvolmachten.polisvoorwaarden.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlockTest {

    private byte[] retrieveFile(String pathToFile) throws IOException {
        Path pdfPath = Paths.get(pathToFile);
        return Files.readAllBytes(pdfPath);
    }

    @Test
    public void testBlockHashing() throws IOException, NoSuchAlgorithmException {
        byte[] autoBytes = retrieveFile("./src/test/resources/Model CF2019 - Combi Autopolis.pdf");
        String autoHex = Utils.sha256Encode(autoBytes);

        Block genesis = new Block("", 0, 0, "", 0, "", "", "");

        Block b1 = new Block(genesis.generateDigest(), 1569616021554L, 1, autoHex, 1, "CF2019", "Combi Autopolis", "BSBVOL");

        byte[] inboedelBytes = retrieveFile("./src/test/resources/B 03.2.41 H - Woonhuis &amp; Inboedelcombinatie.pdf");
        String inboedelHex = Utils.sha256Encode(inboedelBytes);

        Block b2 = new Block(b1.generateDigest(), 1569617021554L, 1, inboedelHex, 1, "B03.2.41H", "Woonhuis & Inboedelcombinatie", "BSBVOL");

        assertEquals("8d117515c4291e71646452965727c86546ef72999c0e5c566d8f710e9c310703", b2.generateDigest());
    }

}
