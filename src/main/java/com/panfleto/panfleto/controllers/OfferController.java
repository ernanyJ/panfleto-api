package com.panfleto.panfleto.controllers;

import com.panfleto.panfleto.DTOs.OfferDto;
import com.panfleto.panfleto.DTOs.UniqueProductDto;
import com.panfleto.panfleto.entities.Offer;
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

    @DeleteMapping("/{marketId}/{offerId}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Long marketId, @PathVariable Long offerId) {
        offerService.deleteOffer(marketId, offerId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Offer> updateOffer(@PathVariable Long id, @RequestParam Long market, @RequestBody OfferDto object) {
        Offer offer = new Offer(object, market, marketService);
        return ResponseEntity.ok().body(offerService.updateOffer(id, offer));
    }

    @PutMapping("{offerId}/add")
    public ResponseEntity<String> addProductToOffer(@RequestBody UniqueProductDto object, @PathVariable Long offerId) {

        // TODO remover logs
        System.out.println("Entrou no addProductToOffer controller");
        System.out.println("product id: " + object.getProductId());
        System.out.println("price: " + object.getPrice());
        System.out.println("offer id: " + offerId);


        offerService.addProductToOffer(offerId, object.getProductId(), object.getPrice());
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto adicionado com sucesso.");
    }

}
