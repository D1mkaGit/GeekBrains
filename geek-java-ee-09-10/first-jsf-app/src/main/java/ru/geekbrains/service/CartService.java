package ru.geekbrains.service;

import ru.geekbrains.service.dto.LineItem;
import ru.geekbrains.service.dto.ProductDto;

import javax.ejb.Stateful;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Stateful
public class CartService {

    private final Map<LineItem, Integer> lineItems = new HashMap<>();

    private final AtomicLong identity = new AtomicLong();

    public List<LineItem> findAll() {
        lineItems.forEach(LineItem::setQty);
        return new ArrayList<>(lineItems.keySet());
    }

    public void addToCart(ProductDto product, Integer qty) {
        LineItem lineItem = new LineItem(product, qty);
        lineItems.put(lineItem, lineItems.getOrDefault(lineItem, 0) + qty);
    }

    public void removeProduct(LineItem lineItem) {
        this.lineItems.remove(lineItem);
    }

    public BigDecimal getTotalCartPrice() {
        BigDecimal totalCartPrice = new BigDecimal(0);
        for (LineItem lineItemKey : lineItems.keySet()) {
            totalCartPrice = totalCartPrice.add(lineItemKey.getPrice().multiply(BigDecimal.valueOf(lineItemKey.getQty())));
        }
        return totalCartPrice;
    }
}
