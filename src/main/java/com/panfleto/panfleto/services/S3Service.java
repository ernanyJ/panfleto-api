package com.panfleto.panfleto.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface S3Service {
    List<String> listObjects(String bucket);

    String getObjectUrl(String key);

    String uploadObject(String bucket, String key, MultipartFile file);
}
