package com.panfleto.panfleto.controllers;

import com.panfleto.panfleto.entities.Market;
import com.panfleto.panfleto.entities.WorkingDays;
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

        List<WorkingDays> workingDaysList = getWorkingDaysList(object);

        String url = s3.uploadObject("supermarket-images", object.get("name").toString(), file);

        Market market = new Market(object, workingDaysList, url);

        return ResponseEntity.status(HttpStatus.CREATED).body(marketService.addMarket(market));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarket(@PathVariable Long id) {
        marketService.deleteMarket(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    private static List<WorkingDays> getWorkingDaysList(JSONObject object) {

        List<WorkingDays> workingDaysList = new ArrayList<>();

        JSONArray jsonWorkingDaysARRAY = object.getJSONArray("workingDays");

        for (int i = 0; i < jsonWorkingDaysARRAY.length(); i++) {

            JSONObject currentDay = jsonWorkingDaysARRAY.getJSONObject(i);

            String weekDay = currentDay.getString("weekDay");
            String openingTime = currentDay.getString("openingTime");
            String closingTime = currentDay.getString("closingTime");

            WorkingDays tempDay = new WorkingDays();

            if (!weekDay.isEmpty()) {
                tempDay.setWeekDay(DaysOfWeek.valueOf(weekDay));
            }

            if (!openingTime.isEmpty()) {
                tempDay.setOpeningTime(LocalTime.parse(openingTime));
            }

            if (!closingTime.isEmpty()) {
                tempDay.setClosingTime(LocalTime.parse(closingTime));
            }

            workingDaysList.add(tempDay);

        }
        return workingDaysList;
    }

}
