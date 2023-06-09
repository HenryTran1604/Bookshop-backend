package com.bookshop.controller;

import com.bookshop.DTO.Book;
import com.bookshop.DTO.Category;
import com.bookshop.entity.BookEntity;
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
    @GetMapping("/category/{cId}/books")
    public List<Book> getBookByCategory(@PathVariable int cId) {
        return categoryService.getBookByCategory(cId);
    }

    @PutMapping("/category/save/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable int id, @RequestBody Category category) {
        if(categoryService.checkUsedCategoryName(category.getCategoryName(), id)) {
            return ResponseEntity.badRequest().body("Thể loại này đã tồn tại!");
        }
        categoryService.updateCategory(category);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/category/save/{id}")
    public ResponseEntity<String> addCategory(@PathVariable int id, @RequestBody Category category) {
        if(categoryService.checkUsedCategoryName(category.getCategoryName(), id)) {
            return ResponseEntity.badRequest().body("Thể loại này đã tồn tại!");
        }
        categoryService.addCategory(category);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/category/delete/{cId}")
    public void deleteCategory(@PathVariable int cId) {
        categoryService.deleteCategory(cId);
    }
}
