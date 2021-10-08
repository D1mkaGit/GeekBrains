package ru.geekbrains.rest;

import ru.geekbrains.persist.Category;
import ru.geekbrains.service.dto.CategoryDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/v1/category")
public interface CategoryResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<CategoryDto> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    CategoryDto findByIdOrException(@PathParam("id") long id);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    CategoryDto insert(CategoryDto category);

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    CategoryDto update(CategoryDto category);

    @DELETE
    @Path("/{id}")
    void delete(@PathParam("id") long id);

}
