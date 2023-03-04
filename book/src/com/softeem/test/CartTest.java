package com.softeem.test;

import com.softeem.bean.CartItem;
import com.softeem.service.Cart;
import org.junit.Test;

import java.math.BigDecimal;

public class CartTest {
    @Test
    public void addItem(){
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"java天通",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(3,"java放弃",1,new BigDecimal(1000),new BigDecimal(1000)));

        System.out.println(cart);
    }

    @Test
    public void deleteItem(){
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"java天通",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(3,"java放弃",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void getTotalCount(){
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"java天通",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(3,"java放弃",1,new BigDecimal(1000),new BigDecimal(1000)));
        System.out.println(cart.getTotalCount());
    }

    @Test
    public void getTotalPrice(){

    }

}
