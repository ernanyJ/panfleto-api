package com.panfleto.panfleto.services.s3;

import com.panfleto.panfleto.config.S3ClientConfig;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.panfleto.panfleto.utils.FormatKey.NormalizeKey;
import static com.panfleto.panfleto.utils.IsImageFile.IsImageCheck;
import static com.panfleto.panfleto.utils.MultipartToFile.convertMultipartFileToFile;


@Service
@Profile("prod")
public class S3ServiceImpl implements S3Service {

    final S3ClientConfig s3ClientConfig;

    final S3Client s3Client;

    public S3ServiceImpl(S3ClientConfig s3ClientConfig, S3Client s3Client) {
        this.s3ClientConfig = s3ClientConfig;
        this.s3Client = s3ClientConfig.createS3Client();
    }

    @Override
    public String uploadObject(String bucket, String key, MultipartFile file) throws IllegalArgumentException {

        key = NormalizeKey(key);

        if (!IsImageCheck(file)) {
            throw new IllegalArgumentException("File is not an image");
        }

        File image = convertMultipartFileToFile(file);

        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucket).key(key).build();
            PutObjectResponse response = s3Client.putObject(putObjectRequest, RequestBody.fromFile(image));
        } catch (S3Exception e) {
            throw new RuntimeException("Failed to upload object to S3: " + e.getMessage(), e);
        } finally {
            if (image.exists() && !image.delete()) {
                System.err.println("Failed to delete temporary file: " + image.getAbsolutePath());
            }
        }

        return System.getenv("PUB_URL") + "/" + key;
    }


}
