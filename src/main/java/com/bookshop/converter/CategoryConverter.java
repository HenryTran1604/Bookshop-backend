package com.bookshop.converter;

import com.bookshop.DTO.Category;
import com.bookshop.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {
    @Autowired
    private BookConverter bookConverter;
    public CategoryEntity toEntity(Category dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(dto.getId());
        entity.setCategoryName(dto.getCategoryName());
        return entity;
    }
    public Category toDto(CategoryEntity entity) {
        Category dto = new Category();
        dto.setId(entity.getId());
        dto.setCategoryName(entity.getCategoryName());
        dto.setBookQuantity(entity.getBookList() == null ? 0 : entity.getBookList().size());
        return dto;
    }
}
