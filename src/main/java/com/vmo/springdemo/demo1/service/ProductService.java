package com.vmo.springdemo.demo1.service;

import com.vmo.springdemo.demo1.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product saveProduct(Product product);
    List<Product> getAllProduct();
    Product updateProduct(Product product);
    String removeProductById(int id);
    Product getProductById(int id);
    Product getProductByName(String name) ;
    List<Product> getAllProductByCategoryId(int id);

}
