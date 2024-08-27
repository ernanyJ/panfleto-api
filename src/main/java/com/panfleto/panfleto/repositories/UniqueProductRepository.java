package com.panfleto.panfleto.repositories;

import com.panfleto.panfleto.entities.UniqueProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniqueProductRepository extends JpaRepository<UniqueProduct, Long> {
}
