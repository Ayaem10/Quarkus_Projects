package org.acme;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.acme.Model.Products;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Path("/ClientCustomer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestClientResource  {

    @Inject
    @RestClient
    Service service;

    @Path("/getAll")
    @GET
    public Response getAll(){
        List<Products> products = service.getall();
        return Response.ok(products).build();
    }

    @Path("/getByName/{name}")
    @GET
    public Response getName(@PathParam("name") String name){
        List<Products> product = service.getByName(name);
        return Response.ok(product).build();

    }

    @POST
    @Path("/CreateProduct")
    public Response Create(Products products){
        try {
            service.CreateProduct(products);
            return Response.ok().build();
        }catch (Exception e ){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }
    @DELETE
    @Path("/DeleteProduct/{id}")
     public Response delete(@PathParam("id") long id){
        try {
            service.Delete(id);
            return Response.ok("Deleted Successfully").build();
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    @PUT
    @Path("Replace_Product/{id}")
    public Response Replace(@PathParam("id") long id ,Products products){
        try {
            service.replace(id , products);
            return Response.ok("Changed Successfully").build();

        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }


}
