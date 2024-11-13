package com.panfleto.panfleto.controllers;

import com.panfleto.panfleto.entities.Market;
import com.panfleto.panfleto.repositories.MarketRepository;
import com.panfleto.panfleto.services.market.MarketService;
import com.panfleto.panfleto.services.s3.S3Service;
import com.panfleto.panfleto.enums.DaysOfWeek;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/market")
public class MarketController {

    private final MarketService marketService;
    private final S3Service s3;

    public MarketController(MarketService marketService, MarketRepository marketRepository, S3Service s3) {
        this.marketService = marketService;
        this.s3 = s3;
    }

    @GetMapping
    public ResponseEntity<List<Market>> getMarket() {
        return ResponseEntity.status(HttpStatus.OK).body(marketService.getMarkets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Market> getMarketById(@PathVariable Long id) {

        var opt = marketService.getMarket(id);

        return opt.map(market -> ResponseEntity.status(HttpStatus.OK).body(market)).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<Market> createMarket(@RequestParam JSONObject object, @RequestParam MultipartFile file) {

        String url = s3.uploadObject("supermarket-images", object.get("name").toString(), file);

        Market market = new Market(object, url);

        return ResponseEntity.status(HttpStatus.CREATED).body(marketService.addMarket(market));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarket(@PathVariable Long id) {
        marketService.deleteMarket(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
