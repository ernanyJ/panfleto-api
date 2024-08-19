package com.panfleto.panfleto.services.offer;


import com.panfleto.panfleto.DTOs.OfferDto;
import com.panfleto.panfleto.entities.Offer;
import com.panfleto.panfleto.repositories.OfferRepository;
import com.panfleto.panfleto.repositories.ProductRepository;
import com.panfleto.panfleto.services.market.MarketService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final MarketService marketService;
    private final ProductRepository productRepository;

    public OfferServiceImpl(OfferRepository offerRepository, MarketService marketService, ProductRepository productRepository) {
        this.offerRepository = offerRepository;
        this.marketService = marketService;
        this.productRepository = productRepository;
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
    public void addProductToOffer(Long offerId, List<Long> productId) {
        Offer offer = getOfferById(offerId);
        productId.forEach(id -> offer.addProduct(productRepository.findById(id).orElse(null)));
        offerRepository.save(offer);
    }
}