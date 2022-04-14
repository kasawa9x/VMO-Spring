package com.vmo.springdemo.demo1.repository;


import com.vmo.springdemo.demo1.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
