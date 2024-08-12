package com.panfleto.panfleto.controllers;

import com.panfleto.panfleto.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
