package org.acm.ShoppingStore;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import org.acm.Entities.Item;

import java.util.List;


@Entity
@Table(name = "Orders")
public class Order extends PanacheEntity {

    @Column
    public String orderDescription;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Item.class)
    public List<Item> cartItems;
    @Column
    public String customerEmail;
    public String password;

    public Order() {
    }

    public Order(String orderDescription, List<Item> cartItems, String customerEmail, String password) {
        this.orderDescription = orderDescription;
        this.cartItems = cartItems;
        this.customerEmail = customerEmail;
        this.password=password;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public List<Item> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<Item> cartItems) {
        this.cartItems = cartItems;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }




}
