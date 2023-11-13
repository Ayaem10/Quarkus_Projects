package org.acm.ShoppingStore;

import org.acm.Authentication.Exceptions.BusinessException;

public class PayPalPayment implements PaymentMethod{

    public  String email;
    public String password;

    public PayPalPayment(String mail, String pass){
        this.email= mail;
        this.password=pass;
    }

    @Override
    public int Pay(int totalPrice) {
        return totalPrice  ;

    }
}
