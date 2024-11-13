package com.panfleto.panfleto.controllers;

import com.panfleto.panfleto.DTOs.OfferDto;
import com.panfleto.panfleto.DTOs.UniqueProductDto;
import com.panfleto.panfleto.entities.Offer;
import com.panfleto.panfleto.exceptions.EntidadeNaoEncontrada;
import com.panfleto.panfleto.services.market.MarketService;
import com.panfleto.panfleto.services.offer.OfferService;
import org.springframework.http.HttpStatus;
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
    public void createOffer(@RequestBody OfferDto object, @RequestParam Long market) {
        Offer offer = new Offer(object, market, marketService);
        marketService.addOffer(market, offer);
        offerService.createOffer(offer);
    }

    @GetMapping
    public ResponseEntity<List<Offer>> getOffer() {
        return ResponseEntity.ok().body(offerService.getAllOffers());
    }

    @DeleteMapping("/{offerId}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Long offerId) {
        offerService.deleteOffer(offerId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Offer> updateOffer(@PathVariable Long id, @RequestBody OfferDto object) {
        return ResponseEntity.ok().body(offerService.updateOffer(id, object));
    }

    @PutMapping("{offerId}/add")
    public ResponseEntity<String> addProductToOffer(@RequestBody UniqueProductDto object, @PathVariable Long offerId) {
        try {
            offerService.addProductToOffer(offerId, object.getProductId(), object.getPrice());
        } catch (EntidadeNaoEncontrada e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto adicionado com sucesso.");
    }

}
