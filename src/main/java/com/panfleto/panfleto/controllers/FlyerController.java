package com.panfleto.panfleto.controllers;

import com.panfleto.panfleto.DTOs.FlyerDto;
import com.panfleto.panfleto.entities.Flyer;
import com.panfleto.panfleto.services.market.MarketService;
import com.panfleto.panfleto.services.Flyer.FlyerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/flyer")
public class FlyerController {

    private final FlyerService flyerService;
    private final MarketService marketService;

    public FlyerController(FlyerService flyerService, MarketService marketService) {
        this.flyerService = flyerService;
        this.marketService = marketService;
    }


    @PostMapping
    public void createFlyer(@RequestBody FlyerDto object) {
        Flyer flyer = new Flyer(object, marketService);
        marketService.addOffer(object.getMarketId(), flyer);
        flyerService.createFlyer(flyer);
    }

    @GetMapping
    public ResponseEntity<List<Flyer>> getFlyer() {
        return ResponseEntity.ok().body(flyerService.getAllFlyers());
    }

    @DeleteMapping("/{marketId}/{offerId}")
    public ResponseEntity<Void> deleteFlyer(@PathVariable Long marketId, @PathVariable Long offerId) {
        flyerService.deleteFlyer(marketId, offerId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flyer> updateFlyer(@PathVariable Long id, @RequestBody FlyerDto object) {
        Flyer flyer = new Flyer(object, marketService);
        return ResponseEntity.ok().body(flyerService.updateFlyer(id, flyer));
    }

    @PutMapping("{offerId}/add")
    public ResponseEntity<String> addProductToFlyer(@PathVariable Long offerId) {

        // TODO: implementar a adição de produtos ao panfleto

       // flyerService.addProductToFlyer(offerId, object.getProductId(), object.getPrice());
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto adicionado com sucesso.");
    }

}
