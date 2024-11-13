package com.panfleto.panfleto.repositories;

import com.panfleto.panfleto.entities.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarketRepository extends JpaRepository<Market, Long> {
    @Query("SELECT m FROM Market m JOIN m.offers o WHERE o.id = :offerId")
    Optional<Market> findByOfferId(@Param("offerId") Long offerId);
}
