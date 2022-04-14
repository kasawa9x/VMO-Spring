package com.vmo.springdemo.demo1.service;

import com.vmo.springdemo.demo1.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<Category> getAllCategory();
    Category updateCategory(Category category);
    Category saveCategory(Category category);

    String removeCategoryById(int id);

    Category getCategoryById(int id);


}
