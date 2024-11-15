package com.panfleto.panfleto.DTOs;
import com.panfleto.panfleto.enums.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OfferDto {

    @NotNull
    @NotEmpty
    private String title;

    private String description;

    private Long marketId;

    @OneToMany
    private List<Category> includedCategories;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;
}
