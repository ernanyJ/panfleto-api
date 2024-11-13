package com.panfleto.panfleto.services.offer;

import com.panfleto.panfleto.DTOs.OfferDto;
import com.panfleto.panfleto.entities.Offer;

import java.util.List;

public interface OfferService {
    List<Offer> getAllOffers();

    Offer getOfferById(Long id);

    Offer createOffer(Offer offer);

    Offer updateOffer(Long id, OfferDto object);

    void deleteOffer(Long offerId);

    void addProductToOffer(Long offerId, Long productId, double price);

}