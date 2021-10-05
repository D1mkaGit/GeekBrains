package ru.geekbrains.rest;

import ru.geekbrains.service.dto.ProductDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/v1/product")
public interface ProductResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductDto> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ProductDto findByIdOrException(@PathParam("id") long id);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ProductDto insert(ProductDto productDto);

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ProductDto update(ProductDto productDto);

    @DELETE
    @Path("/{id}")
    void delete(@PathParam("id") long id);
}
