package com.panfleto.panfleto.DTOs;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class MarketDto {

    private String name;

    private String address;

    private MultipartFile image;

}
