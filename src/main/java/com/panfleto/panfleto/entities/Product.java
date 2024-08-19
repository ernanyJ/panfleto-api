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
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imgUrl;

    @Column(nullable = false)
    private String name;

    @ElementCollection(targetClass = Category.class)
    @Enumerated(EnumType.STRING)
    private List<Category> categories;

    private double price;

    public Product(JSONObject object) {
        this.setName(object.getString("name"));
        this.setPrice(object.getDouble("price"));
        JSONArray categories = object.getJSONArray("categories");
        this.categories = new ArrayList<>();

        for (Object item : categories) {
            this.categories.add(Category.valueOf(item.toString()));
        }

    }

    public Product(ProductDto object) {
        this.setName(object.getName());
        this.setPrice(object.getPrice());
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
