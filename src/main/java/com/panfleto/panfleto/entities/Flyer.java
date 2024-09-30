package com.panfleto.panfleto.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.panfleto.panfleto.DTOs.FlyerDto;
import com.panfleto.panfleto.enums.Category;
import com.panfleto.panfleto.services.market.MarketService;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Flyer {
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
    List<Product> products;

    @ElementCollection(targetClass = Category.class)
    @Enumerated(EnumType.STRING)
    private List<Category> includedCategories;


    private LocalDate startDate;

    private LocalDate endDate;

    public Flyer(FlyerDto object, MarketService marketService) {
        this.setMarket(marketService.getMarket(object.getMarketId()).get());
        this.setStartDate(object.getStartDate());
        this.setDescription(object.getDescription());
        this.setEndDate(object.getEndDate());
        this.setTitle(object.getTitle());
        this.setIncludedCategories(object.getIncludedCategories());
    }

    public Flyer() {

    }

}
