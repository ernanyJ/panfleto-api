package com.panfleto.panfleto.services;

import java.io.File;
import java.util.List;

public class S3ServiceImpl implements S3Service {
    @Override
    public List<String> listObjects(String bucket) {
        return List.of();
    }

    @Override
    public void uploadObject(String bucket, String key, File file) {

    }

    @Override
    public String getObjectUrl(String object, String key) {
        return "";
    }
}
