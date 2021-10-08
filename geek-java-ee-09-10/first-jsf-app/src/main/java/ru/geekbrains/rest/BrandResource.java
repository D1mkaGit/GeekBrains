package ru.geekbrains.rest;

import ru.geekbrains.persist.Brand;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/v1/brand")
public interface BrandResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<Brand> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Brand findByIdOrException(@PathParam("id") long id);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Brand insert(Brand brand);

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Brand update(Brand brand);

    @DELETE
    @Path("/{id}")
    void delete(@PathParam("id") long id);

}
