package ru.geekbrains.rest;

import ru.geekbrains.persist.Category;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/v1/category")
public interface CategoryResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<Category> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Category findByIdOrException(@PathParam("id") long id);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Category insert(Category category);

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Category update(Category category);

    @DELETE
    @Path("/{id}")
    void delete(@PathParam("id") long id);

}
