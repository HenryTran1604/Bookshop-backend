package com.bookshop.converter;

import com.bookshop.DTO.Register;
import com.bookshop.DTO.User;
import com.bookshop.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    @Autowired
    private PurchaseConverter purchaseConverter;
    public UserEntity toEntity(User dto) {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setPassword("user1");
        entity.setFullName(dto.getFullName());
        entity.setEmail(dto.getEmail());
        entity.setRole(dto.getRole());
        entity.setAvatarUrl(dto.getAvatarUrl());
        entity.setActive(dto.isActive());
        return entity;
    }
    public UserEntity toEntity(Register dto) {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setFullName(dto.getFullName());
        entity.setEmail(entity.getEmail());
        entity.setRole(dto.getRole());
        entity.setAvatarUrl(dto.getAvatarUrl());
        entity.setActive(true);
        return entity;
    }
    public User toDto(UserEntity entity) {
        User dto = new User();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setFullName(entity.getFullName());
        dto.setRole(entity.getRole());
        dto.setAvatarUrl(entity.getAvatarUrl());
        dto.setActive(entity.isActive());
        return dto;
    }
}
