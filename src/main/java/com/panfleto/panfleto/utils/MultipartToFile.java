package com.panfleto.panfleto.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class MultipartToFile {


    public static File convertMultipartFileToFile(MultipartFile multipartFile) {
        File tempFile = new File(System.getProperty("java.io.tmpdir"), multipartFile.getOriginalFilename());
        try {
            multipartFile.transferTo(tempFile);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert MultipartFile to File: " + e.getMessage(), e);
        }
        return tempFile;
    }
}
