package com.panfleto.panfleto.services.uniqueproduct;


import com.panfleto.panfleto.entities.UniqueProduct;
import com.panfleto.panfleto.repositories.UniqueProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniqueProductServiceImpl implements UniqueProductService {

    UniqueProductRepository repository;

    UniqueProductServiceImpl(UniqueProductRepository repository){
        this.repository = repository;
    }

    @Override
    public List<UniqueProduct> getAllProducts() {
        return repository.findAll();
    }
}
