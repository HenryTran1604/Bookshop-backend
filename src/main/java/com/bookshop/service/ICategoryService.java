package com.bookshop.service;

import com.bookshop.DTO.Category;

import java.util.List;

public interface ICategoryService {
    public List<Category> getAllCategories();
    public Category getCategoryByID(int cID);
    public Category addCategory(Category category);
    public Category updateCategory(Category category);
    public void deleteCategory(int cID);
}
