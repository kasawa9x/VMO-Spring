package com.vmo.springdemo.demo1.repository;


import com.vmo.springdemo.demo1.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByName(String name);

    List<Product> findAllByCategory_Id(int id);
}
