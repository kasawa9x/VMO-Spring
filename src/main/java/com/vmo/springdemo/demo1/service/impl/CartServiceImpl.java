package com.vmo.springdemo.demo1.service.impl;

import com.vmo.springdemo.demo1.exception.CartItemNotExistException;
import com.vmo.springdemo.demo1.models.Cart.AddToCart;
import com.vmo.springdemo.demo1.models.Cart.Cart;
import com.vmo.springdemo.demo1.models.Cart.CartDto;
import com.vmo.springdemo.demo1.models.Cart.CartItem;
import com.vmo.springdemo.demo1.models.Product;
import com.vmo.springdemo.demo1.models.User;
import com.vmo.springdemo.demo1.repository.CartRepository;
import com.vmo.springdemo.demo1.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart addToCart(AddToCart addToCart, Product product, User user) {
        Cart cart = new Cart(product, addToCart.getQuantity(), user);
        cartRepository.save(cart);
        return cart;
    }

    @Override
    public CartDto listCartItem(User user) {
        List<Cart> cartList = cartRepository.findAllByUser(user);
        List<CartItem> cartItemList = new ArrayList<>();
        double totalCost = 0;
        for (Cart cart : cartList) {
            CartItem cartItem = getCartItemFromCart(cart);
            cartItemList.add(cartItem);
        }
        for (CartItem cartItem : cartItemList) {
            totalCost += (cartItem.getProduct().getPrice() * cartItem.getQuantity());
            System.out.println(cartItem);
        }
        return new CartDto(cartItemList, totalCost);

    }

    private CartItem getCartItemFromCart(Cart cart) {
        return new CartItem(cart);
    }

    @Override
    public Cart updateCart(AddToCart addToCart, User user, Product product) {
        Cart cart = cartRepository.getById(addToCart.getId());
        cart.setQuantity(addToCart.getQuantity());
        cart.setCreatedDate(new Date());
        cartRepository.save(cart);
        return cart;
    }

    @Override
    public void removeCartItem(int id, Long userId) throws CartItemNotExistException {
        if (!cartRepository.existsById(id))
            throw new CartItemNotExistException("Cart id is invalid : " + id);
        cartRepository.deleteById(id);
    }

    @Override
    public void deleteUserCartItems(User user) {
        cartRepository.deleteByUser(user);
    }


}
