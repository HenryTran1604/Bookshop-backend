package com.bookshop.service.Impl;

import com.bookshop.DTO.Category;
import com.bookshop.converter.CategoryConverter;
import com.bookshop.repository.CategoryRepository;
import com.bookshop.service.ICategoryService;
import com.bookshop.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryConverter categoryConverter;
    public List <Category> getAllCategories() {
        List<CategoryEntity> entities = categoryRepository.findAll();
        List<Category> responses = entities.stream().map(categoryConverter::toDto).toList();
        return responses;
    }

    @Override
    public Category getCategoryByID(int cID) {
        Optional<CategoryEntity> entity = categoryRepository.findById(cID);
        if(entity.isPresent()) return categoryConverter.toDto(entity.get());
        return new Category();
    }

    @Override
    public Category addCategory(Category category) {
        CategoryEntity entity = categoryConverter.toEntity(category);
        Category response = categoryConverter.toDto(categoryRepository.save(entity));
        return response;
    }

    @Override
    public Category updateCategory(Category category) {
        CategoryEntity entity = categoryConverter.toEntity(category);
        Category response = categoryConverter.toDto(categoryRepository.save(entity));
        return response;
    }

    @Override
    public void deleteCategory(int cID) {
        categoryRepository.deleteById(cID);
    }

}
