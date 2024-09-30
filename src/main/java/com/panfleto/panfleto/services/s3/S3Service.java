package com.panfleto.panfleto.services.s3;

import org.springframework.web.multipart.MultipartFile;


public interface S3Service {
    String uploadObject(String bucket, String key, MultipartFile file);
}
