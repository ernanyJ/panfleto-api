package com.panfleto.panfleto.entities;


import com.panfleto.panfleto.DTOs.ProductDto;
import com.panfleto.panfleto.enums.Category;
import jakarta.persistence.*;
import lombok.Data;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imgUrl;

    @Column(nullable = false)
    private String name;

    private String normalizedName;

    @ElementCollection(targetClass = Category.class)
    @Enumerated(EnumType.STRING)
    private List<Category> categories;

    public Product(JSONObject object) {
        this.setName(object.getString("name"));
        JSONArray categories = object.getJSONArray("categories");
        this.categories = new ArrayList<>();

        for (Object item : categories) {
            this.categories.add(Category.valueOf(item.toString()));
        }

    }

    public Product(ProductDto object) {
        this.setName(object.getName());
        this.setImgUrl(object.getImgurl());
        List<Category> list = object.getCategories();
        this.categories = new ArrayList<>();

        for (Object item : list) {
            this.categories.add(Category.valueOf(item.toString()));
        }

    }

    public Product() {
    }
}
