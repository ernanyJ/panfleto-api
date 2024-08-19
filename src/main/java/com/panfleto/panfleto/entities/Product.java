package com.panfleto.panfleto.entities;


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
        this.setImgUrl(object.getString("imgUrl"));
        JSONArray categories = object.getJSONArray("categories");
        this.categories = new ArrayList<>();

        for (Object item : categories) {
            this.categories.add(Category.valueOf(item.toString()));
        }

    }

    public Product() {
    }
}
