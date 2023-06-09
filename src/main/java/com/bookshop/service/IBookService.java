package com.bookshop.service;

import java.util.List;
import java.util.Set;

import com.bookshop.DTO.Book;
import com.bookshop.DTO.Category;
import org.springframework.web.multipart.MultipartFile;

public interface IBookService {
    boolean checkDuplicated(Book book);
    List <Book> getAllBooks();
    List<Book> getAllBooksAvailable();
    List <Book> getBookByTitleContaining(String title);
    Set<String> getAllAuthors();
    List<Book> getBookByAuthor(String author);
    Book getBookByID(int bId);
    Book addBook(Book book);
    Book updateBook(Book book);
    Book setNotAvailable(int bId);
    void deleteBook(int bId);
}

