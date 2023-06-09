package com.bookshop.service;

import com.bookshop.DTO.Book;
import com.bookshop.DTO.Category;

import java.util.List;

public interface ICategoryService {
    boolean checkUsedCategoryName(String name, int id);
    List<Book> getBookByCategory(int cId);
    public List<Category> getAllCategories();
    public Category getCategoryByID(int cID);
    public Category addCategory(Category category);
    public Category updateCategory(Category category);
    public void deleteCategory(int cID);
}
