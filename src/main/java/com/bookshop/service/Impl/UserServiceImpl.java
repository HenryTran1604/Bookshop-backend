package com.bookshop.service.Impl;

import com.bookshop.repository.UserRepository;
import com.bookshop.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {
    @Autowired
    private UserRepository userRepository;

    public boolean checkDuplicate(UserEntity user) {
        return userRepository.findByUsername(user.getUsername(), user.getId()) != null;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserByID(int uID) {
        Optional<UserEntity> user = userRepository.findById(uID);
        if (user.isPresent())
            return user.get();
        return new UserEntity();
    }

    public UserEntity getUserByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public void addUser(UserEntity user) {
        userRepository.save(user);
    }

    public void updateUser(UserEntity user) { userRepository.save(user); }

    public void deleteUser(int uID) {
        userRepository.deleteById(uID);
    }
}
