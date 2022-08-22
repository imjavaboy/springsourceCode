package com.gbq.sourceCode.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/22
 * @Copyright 总有一天，会见到成功
 */
@RestController
public class FileController {

    @GetMapping("/getFile/")
    public byte[] getFile() throws IOException {

        InputStream inputStream = new FileInputStream(new File("G:\\ShiyanshiProject\\SpringSourceCode\\src\\main\\java\\com\\gbq\\sourceCode\\hello.pdf"));


        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inputStream.close();
        return outStream.toByteArray();
    }
}
