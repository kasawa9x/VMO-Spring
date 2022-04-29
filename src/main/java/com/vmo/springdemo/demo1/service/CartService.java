package com.vmo.springdemo.demo1.service;

import com.vmo.springdemo.demo1.models.Cart.AddToCart;
import com.vmo.springdemo.demo1.models.Cart.Cart;
import com.vmo.springdemo.demo1.models.Cart.CartDto;
import com.vmo.springdemo.demo1.models.Product;
import com.vmo.springdemo.demo1.models.User;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    Cart addToCart(AddToCart addToCart, Product product, User user);

    CartDto listCartItem(User user);

    Cart updateCart(AddToCart addToCart, User user, Product product);


    void removeCartItem(int id, Long userId);

    void deleteUserCartItems(User user);

}
