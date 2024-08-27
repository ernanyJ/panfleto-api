package com.panfleto.panfleto.tests;

import com.panfleto.panfleto.services.s3.S3Service;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.ArrayList;

import static com.panfleto.panfleto.utils.FormatKey.NormalizeKey;
public class MockS3ServiceImpl implements S3Service {

    @Override
    public String uploadObject(String bucket, String key, MultipartFile file) {
        // Simula o upload retornando uma URL mock
        key = NormalizeKey(key);
        return "http://mock-url/" + key;
    }
}