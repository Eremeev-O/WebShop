package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Component
@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> productBasket;

    public ProductBasket(Map<UUID, Integer> productBasket) {
        this.productBasket = productBasket;
    }

    public void addProdictToBasket (UUID id){
        productBasket.merge(id,1, Integer::sum);
    }

    public Map<UUID, Integer> getProductBasket() {
        return Collections.unmodifiableMap(productBasket);
    }
}
