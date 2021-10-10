package ru.geekbrains.controller;

import ru.geekbrains.service.CartService;
import ru.geekbrains.service.dto.LineItem;
import ru.geekbrains.service.dto.ProductDto;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@SessionScoped
@Named
public class CartController implements Serializable {

    @EJB
    private CartService cartService;

    public List<LineItem> findAll() {
        return cartService.findAll();
    }

    public void addSingleProductToCart(ProductDto product) {
        addQtyOfProductsToCart(product, 1);
    }

    public void addQtyOfProductsToCart(ProductDto product, int qty) {
        cartService.addToCart(product, qty);
    }

    public void removeFromCart(LineItem lineItem) {
        cartService.removeProduct(lineItem);
    }

    public BigDecimal getTotalCartPrice() {
        return cartService.getTotalCartPrice();
    }

    public void emptyCart() {
        for (LineItem item : findAll()) {
            cartService.removeProduct(item);
        }
    }
}
