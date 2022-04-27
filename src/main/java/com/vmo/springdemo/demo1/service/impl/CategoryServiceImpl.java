package com.vmo.springdemo.demo1.service.impl;


import com.vmo.springdemo.demo1.dto.CategoryDTO;
import com.vmo.springdemo.demo1.dto.CategoryMapper;
import com.vmo.springdemo.demo1.models.Category;
import com.vmo.springdemo.demo1.repository.CategoryRepository;
import com.vmo.springdemo.demo1.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }//findAll

    @Override
    public Category updateCategory(Category category) {
        Category existingCat = categoryRepository.findById(category.getId()).orElse(null);
        existingCat.setName(category.getName());

        return categoryRepository.save(existingCat);
    }

    @Override
    public Category saveCategory(Category category) {
        categoryRepository.save(category);
        return category;
    }

    @Override
    public String removeCategoryById(int id) {
        categoryRepository.deleteById(id);
        return "category removed !! " + id;
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
