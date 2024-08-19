package com.panfleto.panfleto.repositories;

import com.panfleto.panfleto.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
