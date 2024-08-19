package com.panfleto.panfleto.services.offer;

import com.panfleto.panfleto.entities.Offer;

import java.util.List;

public interface OfferService {
    List<Offer> getAllOffers();

    Offer getOfferById(Long id);

    Offer createOffer(Offer offer);

    Offer updateOffer(Long id, Offer offer);

    void deleteOffer(Long marketId, Long offerId);

    void addProductToOffer(Long offerId, List<Long> productId);

}