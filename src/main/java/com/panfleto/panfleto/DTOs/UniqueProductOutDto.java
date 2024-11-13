package com.panfleto.panfleto.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.panfleto.panfleto.entities.Offer;
import com.panfleto.panfleto.entities.Product;
import com.panfleto.panfleto.entities.UniqueProduct;
import jakarta.persistence.*;
import lombok.Data;

@Data
//@JsonPropertyOrder({""})
public class UniqueProductOutDto {



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

        public UniqueProductOutDto(Product product, Offer offer, double price) {
            this.product = product;
            this.offer = offer;
            this.price = price;
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
