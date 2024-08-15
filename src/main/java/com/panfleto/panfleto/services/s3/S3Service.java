package com.panfleto.panfleto.services.s3;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface S3Service {
    List<String> listObjects(String bucket);

    String getObjectUrl(String key);

    String uploadObject(String bucket, String key, MultipartFile file);
}
