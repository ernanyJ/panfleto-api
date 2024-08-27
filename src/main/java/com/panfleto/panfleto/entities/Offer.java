package com.panfleto.panfleto.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.panfleto.panfleto.DTOs.OfferDto;
import com.panfleto.panfleto.enums.Category;
import com.panfleto.panfleto.services.market.MarketService;
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

    @ElementCollection(targetClass = Category.class)
    @Enumerated(EnumType.STRING)
    private List<Category> includedCategories;

    @OneToMany
    private List<UniqueProduct> products;

    private LocalDate startDate;

    private LocalDate endDate;

    public Offer(OfferDto object, MarketService marketService) {
        this.setMarket(marketService.getMarket(object.getMarketId()));
        this.setStartDate(object.getStartDate());
        this.setDescription(object.getDescription());
        this.setEndDate(object.getEndDate());
        this.setTitle(object.getTitle());
        this.setIncludedCategories(object.getIncludedCategories());
    }

    public Offer() {

    }

    public void addProduct(UniqueProduct product) {
        this.products.add(product);
    }

}
