package com.bookshop.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    private int id;
    private String title;
    private String author;
    private Date publicationDate;
    private int pages;
    private int price;
    private Category category;
    private int sold;
    private int rate;
    private int commentNum;
    private String imageUrl;
    private String description;
    private int available;
}
