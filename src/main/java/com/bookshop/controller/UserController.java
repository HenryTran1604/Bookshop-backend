package com.bookshop.controller;

import com.bookshop.DTO.User;
import com.bookshop.converter.UserConverter;
import com.bookshop.entity.UserEntity;
import com.bookshop.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserConverter userConverter;

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam String username, @RequestParam String password) {
        UserEntity entity = userService.getUserByUsernameAndPassword(username, password);
        if(entity != null) {
            return new ResponseEntity<>(userConverter.toDto(entity), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
