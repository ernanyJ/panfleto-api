package com.panfleto.panfleto.tests;

import com.panfleto.panfleto.services.s3.S3Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.ArrayList;

import static com.panfleto.panfleto.utils.FormatKey.NormalizeKey;

public class MockS3ServiceImpl implements S3Service {

    @Override
    public List<String> listObjects(String bucket) {
        return new ArrayList<>();
    }

    @Override
    public String getObjectUrl(String key) {
        return "http://mock-url/" + key;
    }

    @Override
    public String uploadObject(String bucket, String key, MultipartFile file) {
        // Simula o upload retornando uma URL mock
        key = NormalizeKey(key);
        return "http://mock-url/" + key;
    }
}