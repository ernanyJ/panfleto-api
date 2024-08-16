package com.panfleto.panfleto.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String imageUrl;

    @ManyToOne
    @JsonIgnore
    private Market market;

    @OneToMany
    private List<Category> includedCategories;

    @OneToMany
    private List<Product> products;

    private LocalDate startDate;

    private LocalDate endDate;
}
