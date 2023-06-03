package com.bookshop.service;

import com.bookshop.DTO.Register;
import com.bookshop.DTO.User;

import java.util.List;

public interface IUserService {
    public boolean checkDuplicated(User user);
    public boolean checkUsedEmail(String username, String email);

    public List<User> getAllUsers();

    public User getUserByID(int bID);

    public User getUserByUsernameAndPassword(String username, String password);

    public Register addUser(Register user);

    public Register updateUser(Register user);

    public void deleteUser(int uID);
}
