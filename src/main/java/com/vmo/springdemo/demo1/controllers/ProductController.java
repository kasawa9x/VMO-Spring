package com.vmo.springdemo.demo1.controllers;


import com.vmo.springdemo.demo1.models.Product;
import com.vmo.springdemo.demo1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
public class ProductController {
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
    @Autowired
    ProductService productService;

    @PostMapping("/product/add")
    public Product addProduct(@RequestBody Product product) {
        return productService.saveProduct(product);    }

    @GetMapping("/product")
    public List<Product> findAllProducts() {
        return productService.getAllProduct();
    }

    @GetMapping("/product/{id}")
    public Product findProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @GetMapping("/product/{name}")
    public Product findProductByName(@PathVariable String name) {
        return productService.getProductByName(name);
    }

    @PutMapping("/product/update")
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        return productService.removeProductById(id);
    }

    @PostMapping("/product/getByCat/{id}")
    public List<Product> allProductByCategoryId(@PathVariable int id) {

        return productService.getAllProductByCategoryId(id);
    }
}
