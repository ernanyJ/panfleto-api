package com.panfleto.panfleto.controllers;

import com.panfleto.panfleto.entities.UniqueProduct;
import com.panfleto.panfleto.services.uniqueproduct.UniqueProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
