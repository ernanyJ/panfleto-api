package com.panfleto.panfleto.services.market;


import com.panfleto.panfleto.entities.Market;

import java.util.List;

public interface MarketService {
    List<Market> getMarkets();

    Market getMarket(Long id);

    Market addMarket(Market market);

    void updateMarket(Market market);

    void deleteMarket(Long id);

}
