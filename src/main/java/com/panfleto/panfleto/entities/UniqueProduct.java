package com.panfleto.panfleto.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity

@JsonPropertyOrder({"productId", "productName", "price", "image"})
public class UniqueProduct {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Offer offer;

    @ManyToOne
    @JsonIgnore
    private Product product;

    private double price;

    public UniqueProduct(Product product, Offer offer, double price) {
        this.product = product;
        this.offer = offer;
        this.price = price;
    }

    public UniqueProduct() {

    }

    @JsonProperty("productId")
    public Long getProductId(){
        return product.getId();
    }

    @JsonProperty("productName")
    public String getProductName() {
        return product.getName();
    }

    @JsonProperty("image")
    public String getImageUrl() {
        return product.getImgUrl();
    }


}
