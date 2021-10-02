package ru.geekbrains.service;

import ru.geekbrains.service.dto.LineItem;
import ru.geekbrains.service.dto.ProductDto;

import javax.ejb.Stateful;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

@Stateful
public class CartService {

    private final Map<LineItem, Integer> lineItems = new HashMap<>();

    private final AtomicLong identity = new AtomicLong();

    public List<LineItem> findAll() {
        return new ArrayList<>(lineItems.keySet());
    }

    public void addToCart(ProductDto product, Integer qty) {
        AtomicBoolean foundLineItemInCart = new AtomicBoolean(false);
        lineItems.forEach(
                (k, v) -> {
                    if (k.getProduct().equals(product)) {
                        k.setQty(k.getQty() + 1);
                        foundLineItemInCart.set(true);
                    }
                });
        if (!foundLineItemInCart.get())
            lineItems.put(new LineItem(product, qty, product.getPrice()), Math.toIntExact(identity.incrementAndGet()));
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
