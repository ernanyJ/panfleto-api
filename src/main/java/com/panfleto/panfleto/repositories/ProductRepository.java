package com.panfleto.panfleto.repositories;

import com.panfleto.panfleto.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameIsContainingIgnoreCase(String name);
}
