package com.panfleto.panfleto.services.uniqueproduct;

import com.panfleto.panfleto.DTOs.UniqueProductOutDto;
import com.panfleto.panfleto.entities.UniqueProduct;

import java.util.List;

public interface UniqueProductService {
    List<UniqueProduct> getAllProducts();

    List<UniqueProduct> getUniqueProductsLike(String query);

    public List<UniqueProduct> getUniqueProductsByMarketId(Long marketId);
}
