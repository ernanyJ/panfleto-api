package com.panfleto.panfleto.config;

import com.panfleto.panfleto.tests.MockS3ServiceImpl;
import com.panfleto.panfleto.services.s3.S3Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Bean
    public S3Service s3Service() {
        return new MockS3ServiceImpl();
    }
}