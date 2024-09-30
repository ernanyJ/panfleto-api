package com.panfleto.panfleto.services.market;


import com.panfleto.panfleto.entities.Market;
import com.panfleto.panfleto.entities.Flyer;

import java.util.List;
import java.util.Optional;

public interface MarketService {
    List<Market> getMarkets();

    Optional<Market> getMarket(Long id);

    Market addMarket(Market market);

    void updateMarket(Market market);

    void deleteMarket(Long id);

    void addOffer(Long id, Flyer flyer);

    void removeOffer(Long marketId, Long offerId);

}
