package com.vmo.springdemo.demo1.controllers;

import com.vmo.springdemo.demo1.common.ApiResponse;
import com.vmo.springdemo.demo1.models.Cart.AddToCart;
import com.vmo.springdemo.demo1.models.Cart.CartDto;
import com.vmo.springdemo.demo1.models.Product;
import com.vmo.springdemo.demo1.models.User;
import com.vmo.springdemo.demo1.repository.UserRepository;
import com.vmo.springdemo.demo1.service.CartService;
import com.vmo.springdemo.demo1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        CartDto cartDto = cartService.listCartItem(user);
        return new ResponseEntity<CartDto>(cartDto, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCart addToCart, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        Product product = productService.getProductById(addToCart.getProductId());
        System.out.println("product to add " + product.getName());
        cartService.addToCart(addToCart, product, user);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added to cart: " + addToCart.getQuantity() + " " + product.getName()), HttpStatus.CREATED);
    }

    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<ApiResponse> updateCartItem(@RequestBody @Valid AddToCart cartDto,
                                                      Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        Product product = productService.getProductById(cartDto.getProductId());
        cartService.updateCart(cartDto, user, product);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated " + cartDto.getQuantity() + " " + product.getName()), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") int itemID, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        Long userId = user.getId();
        cartService.removeCartItem(itemID, userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);
    }
}

