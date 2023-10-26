package org.acme;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import net.bytebuddy.implementation.bytecode.Throw;
import org.acme.Exceptions.MyException;
import org.acme.Exceptions.services.ProductService;


import javax.naming.directory.InvalidAttributesException;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Map;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    private  ProductService productService;

    @GET
    public Response getProductsAll() throws MyException{
        List<Products> products = productService.getProductAll();
        if (products.isEmpty()){
            throw new MyException(Response.Status.NOT_FOUND.getStatusCode(), "There in No Products Here");
         }
        return Response.ok(products).build();

    }
    @GET
    @Path("/productName/{productName}")
    public Response getProductsByName(@PathParam("productName") String name) throws MyException {

            List<Products> products = productService.getProductsByName(name);

            if (products.isEmpty()){
                throw new MyException(Response.Status.NOT_FOUND.getStatusCode() ,"There is no product named "+ name);
            }
            return Response.ok(products).build();
    }
    @GET
    @Path("/Brand/{BrandName}")
    public Response getByBrand(@PathParam("BrandName") String brand) throws MyException{
        List<Products> products = productService.getProductsByBrand(brand);

        if (products.isEmpty()){
            throw new MyException(Response.Status.NOT_FOUND.getStatusCode() , "There is no product with Brand  "+ brand);
        }
        return Response.ok(products).build();
    }


    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") long id) throws MyException{

            final var productByIdOpt = productService.findProductById(id);
            if (productByIdOpt.isEmpty()) {
                throw new MyException(Response.Status.NOT_FOUND.getStatusCode(),"Tere is No product with ID " + id);
            }

            return Response.ok(productByIdOpt.get()).build();
        }


    @POST
    public Response create(Products product) throws MyException {
        try {
            productService.create(product);
            return Response.noContent().build();
        } catch (Exception e) {
          throw new MyException(Response.Status.BAD_REQUEST.getStatusCode(), " There is something Wrong , Please try again");
        }
    }

    @PUT
    @Path("/{id}")
    public Response replace(@PathParam("id") long productId, Products product) {
        try {
            return Response.ok(productService.Update(productId, product)).build();
        } catch (Exception e) {
            if (e instanceof InvalidParameterException) {
                return Response.status(Response.Status.NOT_FOUND).entity(Map.of("message", e.getMessage())).build();
            }

            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long productId) throws MyException{
        var isDeleted = productService.delete(productId);
        if (!isDeleted) {
            throw new MyException(Response.Status.NOT_FOUND.getStatusCode(), " There is something Wrong , Please make sure that id is Correct");
        }
        return Response.noContent().build();
    }

}


