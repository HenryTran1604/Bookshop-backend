package com.bookshop.controller;

import com.bookshop.DTO.Register;
import com.bookshop.DTO.User;
import com.bookshop.converter.RegisterConverter;
import com.bookshop.converter.UserConverter;
import com.bookshop.entity.UserEntity;
import com.bookshop.service.Impl.FileServiceImpl;
import com.bookshop.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/")
public class UserController {
    private static final String imageDirectory = "../frontend/public/static/avatars/";

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RegisterConverter registerConverter;
    @Autowired
    private FileServiceImpl fileService;

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam(required = false) String username, @RequestParam String password) {
        User user = userService.getUserByUsernameAndPassword(username, password);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }
    @PostMapping("users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @PostMapping("/user")
    public User getUser(@RequestParam int id) {
        User user = userService.getUserByID(id);
        return user;
    }

    @PutMapping("/user/save/{uid}")
    public ResponseEntity<User> updateUser(@RequestParam(required = false) MultipartFile avatar, @RequestParam int id,
                                             @RequestParam String username, @RequestParam String password,
                                             @RequestParam String fullName, @RequestParam String email,
                                             @RequestParam String avatarUrl, @RequestParam String role, @RequestParam boolean active) {
        Register user = new Register(id, username, password, fullName, email, avatarUrl, role, active);
        System.out.println(email );

        if (userService.checkUsedEmail(username, email)) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Email đã được nguời khác sử dụng");
        }
        if (avatar != null) {
            String fileName = fileService.saveFile(avatar, imageDirectory);
            if (fileName.equals("error")) {
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"File không đúng định dạng");
            }
            user.setAvatarUrl("/static/avatars/" + fileName);
        }System.out.println(user.getAvatarUrl());
        userService.updateUser(user);

        return new ResponseEntity<>(registerConverter.toDtoUser(user), HttpStatus.OK);
    }

    @PostMapping("/user/save/{uid}")
    public ResponseEntity<User> addUser(@RequestParam(required = false) MultipartFile avatar, @RequestParam int id,
                                          @RequestParam String username, @RequestParam String password,
                                          @RequestParam String fullName, @RequestParam String email,
                                          @RequestParam String avatarUrl, @RequestParam String role, @RequestParam boolean active) {
        Register user = new Register(id, username, password, fullName, email, avatarUrl, role, active);
        if (userService.checkUsedEmail(username, email)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email đã được nguời khác sử dụng");
        }
        if(userService.checkUsedUsername(username)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Username đã được nguời khác sử dụng");
        }
        if (avatar != null) {
            String fileName = fileService.saveFile(avatar, imageDirectory);
            if (fileName.equals("error")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"File không đúng định dạng");
            }
            user.setAvatarUrl("/static/avatars/" + fileName);
        }
        userService.addUser(user);
        System.out.println(user.getAvatarUrl());
        return new ResponseEntity<>(registerConverter.toDtoUser(user), HttpStatus.OK);
    }
}
