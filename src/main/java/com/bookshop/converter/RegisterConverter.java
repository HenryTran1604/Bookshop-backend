package com.bookshop.converter;

import com.bookshop.DTO.Register;
import com.bookshop.DTO.User;
import com.bookshop.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class RegisterConverter {
    public UserEntity toEntity(Register dto) {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setFullName(dto.getFullName());
        entity.setEmail(dto.getEmail());
        entity.setRole(dto.getRole());
        entity.setAvatarUrl(dto.getAvatarUrl());
        entity.setActive(dto.isActive());
        return entity;
    }
    public Register toDtoRegister(UserEntity entity) {
        Register dto = new Register();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setEmail(entity.getEmail());
        dto.setFullName(entity.getFullName());
        dto.setRole(entity.getRole());
        dto.setAvatarUrl(entity.getAvatarUrl());
        dto.setActive(entity.isActive());
        return dto;
    }
    public User toDtoUser(Register register) {
        User user = new User();
        user.setId(register.getId());
        user.setUsername(register.getUsername());
        user.setEmail(register.getEmail());
        user.setFullName(register.getFullName());
        user.setAvatarUrl(register.getAvatarUrl());
        user.setActive(register.isActive());
        user.setRole(register.getRole());
        return user;
    }
}
