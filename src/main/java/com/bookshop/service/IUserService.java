package com.bookshop.service;

import com.bookshop.DTO.User;

import java.util.List;

public interface IUserService {
    public boolean checkDuplicate(User user);

    public List<User> getAllUsers();

    public User getUserByID(int bID);

    public User getUserByUsernameAndPassword(String username);

    public User addUser(User user);

    public User updateUser(User user);

    public void deleteUser(int uID);
}
