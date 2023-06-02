package com.bookshop.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private int id;
    private String username;
    private String fullName;
    private String email;
    private String avatarUrl;
    private String role;
    private boolean active ;

}
