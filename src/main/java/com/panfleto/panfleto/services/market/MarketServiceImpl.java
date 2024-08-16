package com.panfleto.panfleto.services.market;

import com.panfleto.panfleto.entities.Market;
import com.panfleto.panfleto.entities.Offer;
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

    public void addOffer(Offer offer) {
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

    public void addOffer(Long id, Offer offer){
        marketRepository.getReferenceById(id).addOffers(offer);
    }

    @Override
    public void removeOffer(Long marketId, Long offerId) {
      marketRepository.getReferenceById(marketId).removeOffer(offerId);
    }

    @Override
    public void deleteMarket(Long id) {
        marketRepository.deleteById(id);
    }

}
