package com.panfleto.panfleto.services;

import com.panfleto.panfleto.config.S3ClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class S3ServiceImpl implements S3Service {

    final S3ClientConfig s3ClientConfig;

    public S3ServiceImpl(S3ClientConfig s3ClientConfig) {
        this.s3ClientConfig = s3ClientConfig;
    }

    @Override
    public List<String> listObjects(String bucket) {
        S3Client s3Client = s3ClientConfig.createS3Client();

        List<String> objectKeys = new ArrayList<>();

        ListObjectsRequest listObjectsRequest = ListObjectsRequest.builder()
                .bucket(bucket)
                .build();

        ListObjectsResponse listObjectsResponse = s3Client.listObjects(listObjectsRequest);

        for (S3Object s3Object : listObjectsResponse.contents()) {
            objectKeys.add(s3Object.key());
        }

        return objectKeys;
    }

    @Override
    public void uploadObject(String bucket, String key, File file) {

    }

    @Override
    public String getObjectUrl(String object, String key) {
        return "";
    }
}
