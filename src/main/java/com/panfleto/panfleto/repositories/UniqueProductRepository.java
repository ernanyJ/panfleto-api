package com.panfleto.panfleto.repositories;

import com.panfleto.panfleto.entities.UniqueProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UniqueProductRepository extends JpaRepository<UniqueProduct, Long> {
    @Query("SELECT u FROM UniqueProduct u WHERE LOWER(u.product.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<UniqueProduct> findByProductNameContainingIgnoreCase(String query);

    @Query("SELECT u FROM UniqueProduct u WHERE u.offer.market.id = :marketId")
    List<UniqueProduct> findAllUniqueProductsByMarketId(Long marketId);
}
