package com.panfleto.panfleto.services.market;

import com.panfleto.panfleto.entities.Market;
import com.panfleto.panfleto.entities.Flyer;
import com.panfleto.panfleto.repositories.MarketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Market> getMarket(Long id) {
        return marketRepository.findById(id);
    }

    @Override
    public Market addMarket(Market market) {
       return marketRepository.save(market);
    }

    public void addOffer(Flyer flyer) {
//        marketRepository.save(new );
    }

    @Override
    public void updateMarket(Market market) {
        System.out.println("CURRENT MARKET =>" + market);
        Market tempMarket = marketRepository.getReferenceById(market.getId());
        tempMarket.setName(market.getName());
        tempMarket.setAddress(market.getAddress());
        tempMarket.setImgUrl(market.getImgUrl());
        marketRepository.save(market);
    }

    public void addOffer(Long id, Flyer flyer){
        marketRepository.getReferenceById(id).addFlyer(flyer);
    }

    @Override
    public void removeOffer(Long marketId, Long offerId) {
      marketRepository.getReferenceById(marketId).removeFlyer(offerId);
    }

    @Override
    public void deleteMarket(Long id) {
        marketRepository.deleteById(id);
    }

}
