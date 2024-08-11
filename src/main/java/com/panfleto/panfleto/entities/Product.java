package com.panfleto.panfleto.entities;


import jakarta.persistence.*;
import lombok.Data;

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

    @OneToMany
    private List<Category> categories;

}
