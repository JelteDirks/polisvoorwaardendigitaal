package com.bsbvolmachten.polisvoorwaarden;

import io.jsonwebtoken.Jws;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PolisvoorwaardenRestController.class)
public class PolisvoorwaardenApiTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void TokenGenerationTest() throws Exception {

        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/api/generate_token")
                .param("expires", "15693436436750")
                .param("company", "BSBVOLMACHTEN")
                .param("issuer", "jeltedirks")
                .param("issuedAt", "1502020402")
        ).andExpect(status().isOk()).andReturn();

        String strJws = result.getResponse().getContentAsString();

        Jws jws = Utils.validateJWS(strJws, Utils.getSecret(new File("./src/main/resources/secret.txt")));

        assertNotNull(jws);
        assertEquals("{iss=jeltedirks, exp=15693436436, sub=BSBVOLMACHTEN, iat=1502020}", jws.getBody().toString());
    }

}
