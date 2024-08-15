package com.panfleto.panfleto.utils;

import org.springframework.web.multipart.MultipartFile;

public class IsImageFile {

    public static boolean IsImageCheck(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.equals("image/jpeg") || contentType != null && contentType.equals("image/png");
    }

}
