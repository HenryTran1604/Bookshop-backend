package com.bookshop.converter;

import com.bookshop.DTO.Comment;
import com.bookshop.entity.CommentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {
    @Autowired
    private BookConverter bookConverter;
    @Autowired
    private UserConverter userConverter;
    public CommentEntity toEntity(Comment dto) {
        CommentEntity entity = new CommentEntity();
        entity.setId(dto.getId());
        entity.setUser(userConverter.toEntity(dto.getUser()));
        entity.setBook(bookConverter.toEntity(dto.getBook()));
        entity.setCommentDate(dto.getCommentDate());
        entity.setStars(dto.getStars());
        entity.setContent(dto.getContent());
        return entity;
    }
    public Comment toDto(CommentEntity entity) {
        Comment dto = new Comment();
        dto.setId(entity.getId());
        dto.setBook(bookConverter.toDto(entity.getBook()));
        dto.setCommentDate(entity.getCommentDate());
        dto.setStars(entity.getStars());
        dto.setUser(userConverter.toDto(entity.getUser()));
        dto.setContent(entity.getContent());
        return dto;
    }
}
