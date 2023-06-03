package com.bookshop.service.Impl;

import com.bookshop.DTO.Book;
import com.bookshop.converter.BookConverter;
import com.bookshop.repository.BookRepository;
import com.bookshop.service.IBookService;
import com.bookshop.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookConverter bookConverter;
    @Override
    public boolean checkDuplicated(Book book) {
        return bookRepository.findByTitleAndAuthor(book.getTitle(), book.getAuthor(), book.getId()) != null;
    }

    @Override
    public List<Book> getAllBooks() {
        List<BookEntity> entities = bookRepository.findAll();
        List<Book> responses = entities.stream().map(bookConverter::toDto).toList();
        return responses;
    }

    @Override
    public List<Book> getBookByTitleContaining(String title) {
        List<BookEntity> entities = bookRepository.findByTitleContaining(title);
        List<Book> response = entities.stream().map(bookConverter::toDto).toList();
        return response;
    }

    @Override
    public Set<String> getAllAuthors() {
        Set<String> authors = bookRepository.findAllAuthor();
        return authors;
    }

    @Override
    public List<Book> getBookByAuthor(String author) {
        List<BookEntity> entities = bookRepository.findAllByAuthor(author);
        List<Book> response = entities.stream().map(bookConverter::toDto).toList();
        return response;
    }

    @Override
    public Book getBookByID(int bID) {
        Optional<BookEntity> entity = bookRepository.findById(bID);
        if(entity.isPresent()) return bookConverter.toDto(entity.get());
        return new Book();
    }

    @Override
    public Book addBook(Book book) {
        BookEntity entity = bookConverter.toEntity(book);
        Book response = bookConverter.toDto(bookRepository.save(entity));
        return response;
    }

    @Override
    public Book updateBook(Book book) {
        BookEntity entity = bookConverter.toEntity(book);
        Book response = bookConverter.toDto(bookRepository.save(entity));
        return response;
    }

    @Override
    public void deleteBook(int bId) {
        bookRepository.deleteById(bId);
    }

}
