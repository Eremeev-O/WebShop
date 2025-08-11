package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.basket.ProductBasket;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addToBasket(UUID id) {
        if (storageService.getProductById(id).isPresent()) {
            productBasket.addProdictToBasket(id);
        } else {
            throw new IllegalArgumentException();
        }
    }
    public UserBasket getUserBasket(){
        Map<UUID, Integer> basketUser = this.productBasket.getProductBasket();
        List<BasketItem> basketItemList = basketUser.entrySet().stream()
                .map(map -> new BasketItem(storageService.getProductById(map.getKey()).orElseThrow(), map.getValue()))
                .collect(Collectors.toCollection(ArrayList::new));
        return new UserBasket(basketItemList);
    }
}
