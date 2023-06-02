package com.bookshop.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comment {
    private int id;
    private User user;
    private Book book;
    private int stars;
    private String content;
    private Date commentDate;
}
