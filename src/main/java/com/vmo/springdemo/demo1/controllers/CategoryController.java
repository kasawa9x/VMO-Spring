package com.vmo.springdemo.demo1.controllers;



import com.vmo.springdemo.demo1.models.Category;
import com.vmo.springdemo.demo1.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @PostMapping("/cat/add")
    public Category addCat(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }
    @GetMapping("/cat")
    public List<Category> findAllCats() {
        return categoryService.getAllCategory();
    }
    @GetMapping("/cat/{id}")
    public Category findCattById(@PathVariable int id) {
        return categoryService.getCategoryById(id);
    }
    @PutMapping("/cat/update")
    public Category updateProduct(@RequestBody Category category) {
        return categoryService.updateCategory(category);
    }
    @DeleteMapping("/cat/delete/{id}")
    public String deleteCat(@PathVariable int id) {
        return categoryService.removeCategoryById(id);
    }
}
