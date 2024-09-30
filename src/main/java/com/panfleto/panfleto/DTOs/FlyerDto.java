package com.panfleto.panfleto.DTOs;
import com.panfleto.panfleto.entities.Product;
import com.panfleto.panfleto.enums.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class FlyerDto {

    @NotNull
    @NotEmpty
    private String title;

    private String description;

    @NotNull
    @NotEmpty
    private Long marketId;

    @OneToMany
    private List<Category> includedCategories;

    private LocalDate startDate;

    private LocalDate endDate;

    @OneToMany
    List<Product> products;
}
