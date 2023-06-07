package com.bookshop.controller;

import com.bookshop.DTO.Category;
import com.bookshop.service.Impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/")

public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return categories;
    }

    @GetMapping("/category/{id}")
    public Category getCategoryById(@PathVariable String id) {
        Category category = categoryService.getCategoryByID(Integer.valueOf(id));
        return category;
    }

    @PutMapping("/category/save/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable String id, @RequestBody Category category) {
        categoryService.updateCategory(category);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/category/save/{id}")
    public ResponseEntity<String> addCategory(@PathVariable String id, @RequestBody Category category) {
        categoryService.addCategory(category);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/category/delete/{cId}")
    public void deleteCategory(@PathVariable int cId) {
        categoryService.deleteCategory(cId);
    }
}
