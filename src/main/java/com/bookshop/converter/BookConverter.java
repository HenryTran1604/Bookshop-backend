package com.bookshop.converter;

import com.bookshop.DTO.Book;
import com.bookshop.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookConverter {
    @Autowired
    private CategoryConverter converter;
    public BookEntity toEntity(Book dto) {
        BookEntity entity = new BookEntity();
        entity.setId(dto.getId());
        entity.setAuthor(dto.getAuthor());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setPages(dto.getPages());
        entity.setPublicationDate(dto.getPublicationDate());
        entity.setCategory(converter.toEntity((dto.getCategory())));
        entity.setPrice(dto.getPrice());
        entity.setImageUrl(dto.getImageUrl());
        return entity;
    }
    public Book toDto(BookEntity entity) {
        Book dto = new Book();
        dto.setId(entity.getId());
        dto.setAuthor(entity.getAuthor());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setPages(entity.getPages());
        dto.setPublicationDate(entity.getPublicationDate());
        dto.setCategory(converter.toDto((entity.getCategory())));
        dto.setPrice(entity.getPrice());
        dto.setImageUrl(entity.getImageUrl());
        dto.setRate(entity.averageRate());
        dto.setSold(entity.sold());
        dto.setCommentNum(entity.getCommentList() == null ? 0 : entity.getCommentList().size());
        return dto;
    }
}
