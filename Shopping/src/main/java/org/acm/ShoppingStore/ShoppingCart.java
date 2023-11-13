package org.acm.ShoppingStore;

import jakarta.inject.Inject;
import org.acm.Entities.Item;

import java.util.List;

public class ShoppingCart {

   public List<Item>items;
    @Inject
    ItemRepository itemRepository;
    public ShoppingCart(Order order , PaymentMethod paymentMethod){
        this.items = order.cartItems;

    }
    public ShoppingCart(Order order){
        this.items = order.cartItems;

    }




}
