package com.bookshop.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Register {
    private int id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String avatarUrl;
    private String role;
    private boolean active ;

}
