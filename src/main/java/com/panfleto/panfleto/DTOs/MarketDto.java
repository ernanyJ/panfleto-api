package com.panfleto.panfleto.DTOs;

import com.panfleto.panfleto.entities.WorkingDays;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class MarketDto {

    private String name;

    private String address;

    private MultipartFile image;

    List<WorkingDays> workingDays;

}
