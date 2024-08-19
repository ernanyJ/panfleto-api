package com.panfleto.panfleto.DTOs;

import com.panfleto.panfleto.enums.Category;
import lombok.Data;

import java.util.List;

@Data
public class ProductDto {

    private String name;
    private String imgurl;
    private List<Category> categories;
    private double price;

}
