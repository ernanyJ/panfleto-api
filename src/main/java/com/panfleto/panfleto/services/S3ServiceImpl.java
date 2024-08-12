package com.panfleto.panfleto.services;

import com.panfleto.panfleto.config.S3ClientConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.File;
import java.io.IOException;
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
        List<String> objectKeys;
        ListObjectsResponse listObjectsResponse;
        try (S3Client s3Client = s3ClientConfig.createS3Client()) {

            objectKeys = new ArrayList<>();

            ListObjectsRequest listObjectsRequest = ListObjectsRequest
                    .builder()
                    .bucket(bucket)
                    .build();

            listObjectsResponse = s3Client.listObjects(listObjectsRequest);
        }

        for (S3Object s3Object : listObjectsResponse.contents()) {
            objectKeys.add(s3Object.key());
        }
        return objectKeys;
    }

    @Override
    public String getObjectUrl(String key) {
        String endpoint = System.getenv("PUB_URL");
        return endpoint + "/" + key;
    }


    @Override
    public String uploadObject(String bucket, String key, MultipartFile file) throws IllegalArgumentException {

        if (!isImageFile(file)) {
            System.out.println("file is not an image");
            throw new IllegalArgumentException("File is not an image");
        }

        File tempFile = convertMultipartFileToFile(file);

        try (S3Client s3Client = s3ClientConfig.createS3Client()) {

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build();

            PutObjectResponse response = s3Client.putObject(putObjectRequest, RequestBody.fromFile(tempFile));


        } catch (S3Exception e) {
            throw new RuntimeException("Failed to upload object to S3: " + e.getMessage(), e);
        } finally {
            if (tempFile.exists() && !tempFile.delete()) {
                System.err.println("Failed to delete temporary file: " + tempFile.getAbsolutePath());
            }
        }

        return System.getenv("PUB_URL") + "/" + key;
    }

    private File convertMultipartFileToFile(MultipartFile multipartFile) {
        File tempFile = new File(System.getProperty("java.io.tmpdir"), multipartFile.getOriginalFilename());
        try {
            multipartFile.transferTo(tempFile);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert MultipartFile to File: " + e.getMessage(), e);
        }
        return tempFile;
    }

    private boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.equals("image/jpeg") || contentType != null && contentType.equals("image/png");
    }


}
