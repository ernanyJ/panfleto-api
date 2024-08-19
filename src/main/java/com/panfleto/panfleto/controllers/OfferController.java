package com.panfleto.panfleto.controllers;

import com.panfleto.panfleto.DTOs.OfferDto;
import com.panfleto.panfleto.entities.Offer;
import com.panfleto.panfleto.services.market.MarketService;
import com.panfleto.panfleto.services.offer.OfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/offer")
public class OfferController {

    private final OfferService offerService;
    private final MarketService marketService;

    public OfferController(OfferService offerService, MarketService marketService) {
        this.offerService = offerService;
        this.marketService = marketService;
    }


    @PostMapping
    public void createOffer(@RequestBody OfferDto object) {
        Offer offer = offerService.offerFromDto(object);
        marketService.addOffer(object.getMarketId(), offer);
        offerService.createOffer(offer);
    }

    @GetMapping
    public ResponseEntity<List<Offer>> getOffer() {
        return ResponseEntity.ok().body(offerService.getAllOffers());
    }

    @DeleteMapping("/{marketId}/{offerId}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Long marketId, @PathVariable Long offerId) {
        offerService.deleteOffer(marketId, offerId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Offer> updateOffer(@PathVariable Long id, @RequestBody OfferDto object) {
        Offer offer = offerService.offerFromDto(object);
        return ResponseEntity.ok().body(offerService.updateOffer(id, offer));
    }

    @PutMapping("/addProduct/{offerId}")
    public ResponseEntity<Void> addProductToOffer(@PathVariable Long offerId, @RequestBody List<Long> productsId) {

        offerService.addProductToOffer(offerId, productsId);
        return ResponseEntity.noContent().build();
    }

}
