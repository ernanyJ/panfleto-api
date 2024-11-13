package com.panfleto.panfleto.controllers;

import com.panfleto.panfleto.DTOs.UniqueProductOutDto;
import com.panfleto.panfleto.entities.UniqueProduct;
import com.panfleto.panfleto.services.uniqueproduct.UniqueProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/uniqueproduct")
public class UniqueProductController {

    UniqueProductService service;

    UniqueProductController (UniqueProductService uniqueProductService) {
        this.service = uniqueProductService;
    }

    @GetMapping
    List<UniqueProduct> getAllUniqueProducts(){
        return service.getAllProducts();
    }

    @GetMapping("/search")
    List<UniqueProduct> getUniqueProductsByNameLike(@RequestParam String query){
        return service.getUniqueProductsLike(query);
    }

    @GetMapping("/market/{marketId}")
    List<UniqueProduct> getUniqueProductsByMarketId(@PathVariable Long marketId){
        return service.getUniqueProductsByMarketId(marketId);
    }

}
