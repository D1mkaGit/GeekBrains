package ru.geekbrains.service;

import ru.geekbrains.service.dto.LineItem;
import ru.geekbrains.service.dto.ProductDto;

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Stateful
public class CartService {

    private final Map<LineItem, Integer> lineItems = new HashMap<>();

    private AtomicLong identity = new AtomicLong();

    public List<LineItem> findAll() {
        lineItems.forEach(LineItem::setQty);
        return new ArrayList<>(lineItems.keySet());
    }

    public void addToCart(ProductDto product, Integer qty) {
        lineItems.put(new LineItem(product, qty, product.getPrice()), Math.toIntExact(identity.incrementAndGet()));
    }

    public void removeProduct(LineItem lineItem) {
        this.lineItems.remove(lineItem);
    }
}
