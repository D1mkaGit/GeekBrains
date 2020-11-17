package ru.geekbrains.service;

import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.service.model.LineItem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public interface CartService extends Serializable {

    void addProductBidPrice(ProductRepr productRepr, BigDecimal bidPrice);
    void removeProduct(LineItem lineItem);
    List<LineItem> getLineItems();
    void updateCart(LineItem lineItem);
    BigDecimal getSubTotal();
}
