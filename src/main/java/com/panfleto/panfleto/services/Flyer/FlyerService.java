package com.panfleto.panfleto.services.Flyer;

import com.panfleto.panfleto.entities.Flyer;

import java.util.List;

public interface FlyerService {
    List<Flyer> getAllFlyers();

    Flyer getFlyerById(Long id);

    Flyer createFlyer(Flyer flyer);

    Flyer updateFlyer(Long id, Flyer flyer);

    void deleteFlyer(Long marketId, Long offerId);

    void addProductToFlyer(Long offerId, Long productId, double price);

}