package com.panfleto.panfleto.services;

import java.io.File;
import java.util.List;

public interface S3Service {
    List<String> listObjects(String bucket);

    void uploadObject(String bucket, String key, File file);

    String getObjectUrl(String object, String key);
}
