package com.bsbvolmachten.polisvoorwaarden;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
public class PolisvoorwaardenRestController {

    @RequestMapping(value = "/api/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("key") String key,
            @RequestParam("title") String title,
            @RequestParam("code") String code) {

        File convertFile = new File("/var/tmp/polisvoorwaarden/" + file.getOriginalFilename());

        System.out.println(key + "-" + title + "-" + code + " -" + file.getOriginalFilename());

        try {
            convertFile.createNewFile();
        } catch (IOException e) {
            System.out.println("error creating new file");
            e.printStackTrace();
            return "Something went wrong";
        }

        try {
            FileOutputStream fOut = new FileOutputStream(convertFile);
            fOut.write(file.getBytes());
            fOut.close();
        } catch (IOException e) {
            System.out.println("error creating file output stream");
            e.printStackTrace();
        }

        String sha = "";
        try {
            sha = Utils.sha256Encode(file.getBytes());
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return sha;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "Home";
    }

}
