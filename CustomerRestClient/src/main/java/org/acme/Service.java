package org.acme;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.Model.Products;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient(baseUri = "http://localhost:9090/")
public interface Service {

    @GET
    List<Products> getall();

    @GET
    @Path("/productName/{productName}")
    List<Products> getByName(@PathParam("productName") String name);

    @POST
    Response CreateProduct(Products products);

    @DELETE
    @Path("/{id}")
    Response Delete(@PathParam("id") long id);

    @PUT
    @Path("/{id}")
    Response replace(@PathParam("id") long id , Products products);

}
