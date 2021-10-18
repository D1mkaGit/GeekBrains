package ru.geekbrains.rest;

import ru.geekbrains.controller.CartController;
import ru.geekbrains.service.dto.LineItem;
import ru.geekbrains.service.dto.ProductDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.List;

@Path("/v1/cart")
public class CartResource {

    @Inject
    private CartController cartController;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LineItem> findAll() {
        return cartController.findAll();
    }

    @GET
    @Path("/total")
    @Produces(MediaType.APPLICATION_JSON)
    public BigDecimal getTotalCartPrice() {
        return cartController.getTotalCartPrice();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void addToCart(ProductDto product) {
        cartController.addSingleProductToCart(product);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void removeProduct(LineItem lineItem) {
        cartController.removeFromCart(lineItem);
    }

    @DELETE
    @Path("/empty")
    public void emptyCart(){
        cartController.emptyCart();
    }

}
