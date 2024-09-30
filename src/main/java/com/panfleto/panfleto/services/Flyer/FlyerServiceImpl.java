package com.panfleto.panfleto.services.Flyer;


import com.panfleto.panfleto.entities.Flyer;
import com.panfleto.panfleto.entities.Product;
import com.panfleto.panfleto.repositories.FlyerRepository;
import com.panfleto.panfleto.repositories.ProductRepository;
import com.panfleto.panfleto.services.market.MarketService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlyerServiceImpl implements FlyerService {

    private final FlyerRepository flyerRepository;
    private final MarketService marketService;
    private final ProductRepository productRepository;

    public FlyerServiceImpl(FlyerRepository flyerRepository, MarketService marketService, ProductRepository productRepository) {
        this.flyerRepository = flyerRepository;
        this.marketService = marketService;
        this.productRepository = productRepository;
    }

    @Override
    public List<Flyer> getAllFlyers() {
        return flyerRepository.findAll();
    }

    @Override
    public Flyer getFlyerById(Long id) {
        Optional<Flyer> offer = flyerRepository.findById(id);
        return offer.orElse(null);
    }

    @Override
    public Flyer createFlyer(Flyer flyer) {
        return flyerRepository.save(flyer);
    }

    @Override
    public Flyer updateFlyer(Long id, Flyer flyer) {
        if (flyerRepository.existsById(id)) {
            flyer.setId(id);
            return flyerRepository.save(flyer);
        }
        return null;
    }

    @Override
    public void deleteFlyer(Long marketId, Long offerID) {
        marketService.getMarket(marketId).get().removeFlyer(offerID);

        flyerRepository.deleteById(offerID);
    }

    @Override
    public void addProductToFlyer(Long offerId, Long productId, double price) {

        Flyer flyer = flyerRepository.getReferenceById(offerId);
        Product product = productRepository.getReferenceById(productId);


        flyerRepository.save(flyer);

    }

}