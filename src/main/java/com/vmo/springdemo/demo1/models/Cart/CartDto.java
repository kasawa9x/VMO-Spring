package com.vmo.springdemo.demo1.models.Cart;

import java.util.List;

public class CartDto {
    private List<CartItem> cartItems;
    private double totalCost;

    public CartDto(List<CartItem> cartItemDtoList, double totalCost) {
        this.cartItems = cartItemDtoList;
        this.totalCost = totalCost;
    }

    public List<CartItem> getcartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItemDtoList) {
        this.cartItems = cartItemDtoList;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
}
