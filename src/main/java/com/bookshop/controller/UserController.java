package com.bookshop.controller;

import com.bookshop.DTO.Register;
import com.bookshop.DTO.User;
import com.bookshop.converter.UserConverter;
import com.bookshop.entity.UserEntity;
import com.bookshop.service.Impl.FileServiceImpl;
import com.bookshop.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/api/")
public class UserController {
    private static final String imageDirectory = "C:\\Users\\QuangHuy\\OneDrive - ptit.edu.vn\\Desktop\\Lap trinh web\\project\\frontend\\public\\static\\avatars\\";

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserConverter userConverter;
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

    @PostMapping("/user")
    public User getUser(@RequestParam int id) {
        User user = userService.getUserByID(id);
        return user;
    }

    @PutMapping("/user/save/{uid}")
    public ResponseEntity<String> upDateUser(@RequestParam(required = false) MultipartFile avatar, @RequestParam int id,
                                             @RequestParam String username, @RequestParam String password,
                                             @RequestParam String fullName, @RequestParam String email,
                                             @RequestParam String avatarUrl, @RequestParam String role, @RequestParam boolean active) {
        Register user = new Register(id, username, password, fullName, email, avatarUrl, role, active);
        System.out.println(email );

        if (userService.checkUsedEmail(username, email)) {
            return ResponseEntity.badRequest().body("Email đã được người khác sử dụng!");
        }
        if (avatar != null) {
            String fileName = fileService.saveFile(avatar, imageDirectory);
            if (fileName.equals("error")) {
                return ResponseEntity.badRequest().body("File không đúng định dạng!");
            }
            user.setAvatarUrl("/static/avatars/" + fileName);
        }
        userService.updateUser(user);
        System.out.println(user.getAvatarUrl());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/save/{uid}")
    public ResponseEntity<String> addUser(@RequestParam(required = false) MultipartFile avatar, @RequestParam int id,
                                          @RequestParam String username, @RequestParam String password,
                                          @RequestParam String fullName, @RequestParam String email,
                                          @RequestParam String avatarUrl, @RequestParam String role, @RequestParam boolean active) {
        Register user = new Register(id, username, password, fullName, email, avatarUrl, role, active);
        System.out.println(id + username + password +  fullName +  email + avatarUrl + role+ active);
        if (userService.checkUsedEmail(username, email)) {
            return ResponseEntity.badRequest().body("Email đã được người khác sử dụng!");
        }
        if (avatar != null) {
            String fileName = fileService.saveFile(avatar, imageDirectory);
            if (fileName.equals("error")) {
                return ResponseEntity.badRequest().body("File không đúng định dạng!");
            }
            user.setAvatarUrl("/static/avatars/" + fileName);
        }
        userService.addUser(user);
        System.out.println(user.getAvatarUrl());
        return ResponseEntity.ok().build();
    }
}
