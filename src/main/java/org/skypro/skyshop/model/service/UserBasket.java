package org.skypro.skyshop.model.service;

import java.util.List;

public class UserBasket {
    private final List<BasketItem> userBasket;
    private final double total;

    public UserBasket(List<BasketItem> userBasket) {
        this.userBasket = userBasket;
        this.total = userBasket.stream()
                .mapToDouble(obj -> obj.getProduct().getCost()*obj.getCount())
                .sum();
    }

    public List<BasketItem> getUserBasket() {
        return userBasket;
    }

    public double getTotal() {
        return total;
    }
}
