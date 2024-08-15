package com.panfleto.panfleto.services.market;

import com.panfleto.panfleto.entities.Market;
import com.panfleto.panfleto.repositories.MarketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketServiceImpl implements MarketService {

    final MarketRepository marketRepository;

    public MarketServiceImpl(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }

    @Override
    public List<Market> getMarkets() {
        return marketRepository.findAll();
    }

    @Override
    public Market getMarket(Long id) {
        return marketRepository.getReferenceById(id);
    }

    @Override
    public Market addMarket(Market market) {
       return marketRepository.save(market);
    }

    @Override
    public void updateMarket(Market market) {
        Market tempMarket = marketRepository.getReferenceById(market.getId());
        tempMarket.setName(market.getName());
        tempMarket.setAddress(market.getAddress());
        tempMarket.setImgUrl(market.getImgUrl());
        marketRepository.save(market);
    }

    @Override
    public void deleteMarket(Long id) {
        marketRepository.deleteById(id);
    }

}
