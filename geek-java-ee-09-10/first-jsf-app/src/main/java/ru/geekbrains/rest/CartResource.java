package ru.geekbrains.rest;

import ru.geekbrains.service.dto.LineItem;
import ru.geekbrains.service.dto.ProductDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.List;

@Path("/v1/cart")
public interface CartResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<LineItem> findAll();

    @GET
    @Path("/total")
    @Produces(MediaType.APPLICATION_JSON)
    BigDecimal getTotalCartPrice();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    void addToCart(ProductDto product, Integer qty);

    @DELETE
    void removeProduct(LineItem lineItem);

}
