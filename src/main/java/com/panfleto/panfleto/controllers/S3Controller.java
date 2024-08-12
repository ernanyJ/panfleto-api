package com.panfleto.panfleto.controllers;

import com.panfleto.panfleto.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/s3")
public class S3Controller {

    private final S3Service s3Service;


    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @GetMapping("/list")
    public ResponseEntity<List<String>> listBucketObjects() {
        return ResponseEntity.ok(s3Service.listObjects("supermarket-images"));
    }

    @GetMapping("/objectUrl/{key}")
    public ResponseEntity<String> getObject(@PathVariable String key) {
        return ResponseEntity.ok(s3Service.getObjectUrl(key));
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadObject(
            @RequestParam String bucket,
            @RequestParam String key,
            @RequestParam MultipartFile file) {


        String url = "";

        try {
            url = s3Service.uploadObject(bucket, key, file);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(url);
    }


}
