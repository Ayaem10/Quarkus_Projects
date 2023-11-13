package org.acm;


import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.annotation.security.PermitAll;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.acm.Authentication.AuthFunction;
import org.acm.Authentication.AuthRepository;
import org.acm.Authentication.Exceptions.BusinessException;
import org.acm.Entities.Item;
import org.acm.Entities.clients;
import org.acm.ShoppingStore.*;

import java.util.List;

@Path("Clients")
public class ShoppingResource {

    @Inject
    AuthRepository authRepository;
    @Inject
    ItemRepository itemRepository;

    @POST
    @PermitAll
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void addClient(clients client) throws BusinessException {
        AuthFunction.addClient(client);
    }
    @GET
    @Path("/AllClients")
    public Response clients(){
        List<clients> clients = authRepository.listAll();
        return Response.ok(clients).build();
    }
    @GET
    @Path("/login")
    public Response login(clients client) throws BusinessException{
        clients existClient = clients.find("username" , client.username).firstResult();
        boolean isAuthenticated = BcryptUtil.matches(client.password ,existClient.getPassword() );

        if (existClient.username!=null & isAuthenticated){
            return Response.ok().build();
        }
        throw new BusinessException(Response.Status.EXPECTATION_FAILED.getStatusCode(),
                " there is something wrong with username or password , Please try again");
    }

    @Transactional
    @POST
    @Path("StoreItem")
    public Response StoreItem(Item item)throws BusinessException{
        if (!ExistItem(item.code)) {
            item.persist();
            return Response.ok("Item Stored Successfully").build();
        }
        throw new BusinessException(Response.Status.NOT_FOUND.getStatusCode(),
                "There is an Item with This Code please Choose different code for this item ");
    }
    @Transactional
    @DELETE
    @Path("RemoveItem")
    public Response RemoveItem(Item item)throws BusinessException{
        if (ExistItem(item.code)) {
           item.delete();

            return Response.ok("Item Deleted Successfully").build();
        }
        throw new BusinessException(Response.Status.NOT_FOUND.getStatusCode(),
                "There is no item with this code ");
    }

    @GET
    @Path("AllItems")
    public Response AllItems(){
       List<Item> items =  Item.listAll();
        return Response.ok(items).build();
    }
    @Transactional
    @POST
    @Path("ShoppingCart")
    public Response ShoppingCart(Order order)throws BusinessException {
        clients existClient = clients.find("username", order.getCustomerEmail()).firstResult();
        boolean isAuthenticated = BcryptUtil.matches(order.password, existClient.password);
        if (existClient.username == null || !isAuthenticated) {
            throw new BusinessException(Response.Status.EXPECTATION_FAILED.getStatusCode(),
                    " there is something wrong with username or password , Please try again");
        } else {
            int total = CalculateTotalPrice(order , new PayPalPayment(order.customerEmail , order.password));
            return Response.ok(total + " Payed with PayPal").build();
        }  }
    private static boolean ExistItem(int code) {
        return (Item.count("code" , code)>0);
    }
    public int CalculateTotalPrice(Order order  , PaymentMethod paymentMethod) {
        ShoppingCart cart = new ShoppingCart(order , paymentMethod);
        int size = cart.items.size();
        int total = 0;
        for (int i = 0; i < size; i++) {
            int code = cart.items.get(i).code;
            Item item = itemRepository.findByCode(code);
            if (item != null) {
                total += item.price;

            } else {
                total = total;
            }

        }
        return paymentMethod.Pay(total);
    }


}
