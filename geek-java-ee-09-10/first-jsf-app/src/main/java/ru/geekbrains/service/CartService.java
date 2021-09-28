package ru.geekbrains.service;

import ru.geekbrains.service.dto.LineItem;
import ru.geekbrains.service.dto.ProductDto;

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateful
public class CartService {

    private final Map<LineItem, Integer> lineItems = new HashMap<>();

    public List<LineItem> findAll() {
        lineItems.forEach(LineItem::setQty);
        return new ArrayList<>(lineItems.keySet());
    }

    public void addToCart(ProductDto product, Integer qty) {
        // TODO
    }

    public void removeProduct(ProductDto product) {
        // TODO
    }

}
