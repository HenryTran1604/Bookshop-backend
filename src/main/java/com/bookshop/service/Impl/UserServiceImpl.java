package com.bookshop.service.Impl;

import com.bookshop.DTO.Register;
import com.bookshop.DTO.User;
import com.bookshop.converter.RegisterConverter;
import com.bookshop.converter.UserConverter;
import com.bookshop.repository.UserRepository;
import com.bookshop.entity.UserEntity;
import com.bookshop.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private RegisterConverter registerConverter;

    @Override
    public boolean checkUsedUsername(String username) {
        return userRepository.findByUsername(username) != null;
    }

    @Override
    public boolean checkUsedEmail(String username, String email) {
        return userRepository.findByUsernameNotAndEmail(username, email) != null;
    }

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> entities = userRepository.findAll();
        List<User> responses = entities.stream().map(userConverter::toDto).toList();
        return responses;
    }

    @Override
    public User getUserByID(int uID) {
        Optional<UserEntity> opt = userRepository.findById(uID);
        if (opt.isPresent())
            return userConverter.toDto(opt.get());
        return new User();
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        UserEntity entity = userRepository.findByUsernameAndPassword(username, password);
        return entity == null ? null : userConverter.toDto(entity);
    }
    @Override
    public Register addUser(Register register) {
        UserEntity entity = registerConverter.toEntity(register);
        Register response = registerConverter.toDtoRegister(userRepository.save(entity));
        return response;
    }
    @Override
    public Register updateUser(Register register) {
        UserEntity entity = registerConverter.toEntity(register);
        Register response = registerConverter.toDtoRegister(userRepository.save(entity));
        return response;
    }
    @Override
    public void deleteUser(int uID) {
        userRepository.deleteById(uID);
    }
}
