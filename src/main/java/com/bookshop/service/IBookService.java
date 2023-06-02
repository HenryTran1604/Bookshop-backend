package com.bookshop.service;

import java.util.List;
import java.util.Set;

import com.bookshop.DTO.Book;
import org.springframework.web.multipart.MultipartFile;

public interface IBookService {
    boolean checkDuplicated(Book book);
    String saveFile(MultipartFile image, Book book);
    List <Book> getAllBooks();
    List <Book> getBookByTitleContaining(String title);
    Set<String> getAllAuthors();
    List<Book> getBookByAuthor(String author);
    Book getBookByID(int bId);
    Book addBook(Book book);
    Book updateBook(Book book);
    void deleteBook(int bId);
}

