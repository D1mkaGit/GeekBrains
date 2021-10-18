package ru.geekbrains.rest;

import ru.geekbrains.service.dto.BrandDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/v1/brand")
public interface BrandResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<BrandDto> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    BrandDto findByIdOrException(@PathParam("id") long id);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    BrandDto insert(BrandDto brand);

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    BrandDto update(BrandDto brand);

    @DELETE
    @Path("/{id}")
    void delete(@PathParam("id") long id);

}
