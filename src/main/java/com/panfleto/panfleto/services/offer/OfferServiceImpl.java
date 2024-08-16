package com.panfleto.panfleto.services.offer;


import com.panfleto.panfleto.DTOs.OfferDto;
import com.panfleto.panfleto.entities.Market;
import com.panfleto.panfleto.entities.Offer;
import com.panfleto.panfleto.repositories.OfferRepository;
import com.panfleto.panfleto.services.market.MarketService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private MarketService marketService;

    public OfferServiceImpl(OfferRepository offerRepository, MarketService marketService) {
        this.offerRepository = offerRepository;
        this.marketService = marketService;
    }

    @Override
    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    @Override
    public Offer getOfferById(Long id) {
        Optional<Offer> offer = offerRepository.findById(id);
        return offer.orElse(null);
    }

    @Override
    public Offer createOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    @Override
    public Offer updateOffer(Long id, Offer offer) {
        if (offerRepository.existsById(id)) {
            offer.setId(id);
            return offerRepository.save(offer);
        }
        return null;
    }

    @Override
    public void deleteOffer(Long marketId, Long offerID) {
        marketService.getMarket(marketId).removeOffer(offerID);

        offerRepository.deleteById(offerID);
    }

    @Override
    public Offer offerFromDto(OfferDto object) {
        Offer offer = new Offer();
        offer.setMarket(marketService.getMarket(object.getMarketId()));
        offer.setStartDate(object.getStartDate());
        offer.setDescription(object.getDescription());
        offer.setEndDate(object.getEndDate());
        offer.setImageUrl(object.getImageUrl());
        offer.setTitle(object.getTitle());
        offer.setIncludedCategories(object.getIncludedCategories());
        return offer;
    }
}