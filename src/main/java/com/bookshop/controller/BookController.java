package com.bookshop.controller;

import com.bookshop.DTO.Book;

import com.bookshop.service.Impl.BookServiceImpl;
import com.bookshop.service.Impl.CategoryServiceImpl;
import com.bookshop.service.Impl.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.sql.Date;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/api/")

public class BookController {
    private static final String imageDirectory = "../frontend/public/static/book-covers/";

    @Autowired
    private BookServiceImpl bookServiceImpl;

    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private FileServiceImpl fileService;

    private Book createBook(Integer id, String title, String author,
                            Integer categoryId, String publicationDate,
                            Integer pages, Integer price,
                            String imageUrl, String description) {
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setCategory(categoryService.getCategoryByID(categoryId));
        book.setPublicationDate(Date.valueOf(publicationDate));
        book.setPages(pages);
        book.setPrice(price);
        book.setImageUrl(imageUrl);
        book.setDescription(description);
        return book;
    }

    @GetMapping("/authors")
    public Set<String> getAllAuthors() {
        return bookServiceImpl.getAllAuthors();
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookServiceImpl.getAllBooks();
    }

    @GetMapping("/book/{bID}")
    public Book getBook(@PathVariable String bID) {
        return bookServiceImpl.getBookByID(Integer.parseInt(bID));
    }

    @GetMapping("/book/search")
    public List<Book> getBookByTitleContaining(@RequestParam String key) {
        return bookServiceImpl.getBookByTitleContaining(key);
    }

    @PutMapping("/book/save/{bID}")
    public ResponseEntity<String> updateBook(@RequestParam(required = false) MultipartFile image, @PathVariable Integer bID, @RequestParam Integer id,
                                             @RequestParam String title, @RequestParam String author, @RequestParam Integer categoryId,
                                             @RequestParam String publicationDate, @RequestParam Integer pages, @RequestParam Integer price,
                                             @RequestParam String imageUrl, @RequestParam String description) {
        System.out.println("PUTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
        Book book = createBook(id, title, author, categoryId, publicationDate, pages, price, imageUrl, description);
        if (bookServiceImpl.checkDuplicated(book)) {
            return ResponseEntity.badRequest().body("Sách đã tồn tại");
        }
        if (image != null) {
            String fileName = fileService.saveFile(image, imageDirectory);
            if (fileName.equals("error")) {
                return ResponseEntity.badRequest().body("File không đúng định dạng!");
            }
            book.setImageUrl("/static/book-covers/" + fileName);
        }
        bookServiceImpl.addBook(book);
        System.out.println(book.getImageUrl());
        return ResponseEntity.ok().build();

    }

    @PostMapping("/book/save/{bID}")
    public ResponseEntity<String> addBook(@RequestParam(required = false) MultipartFile image, @PathVariable Integer bID, @RequestParam Integer id,
                                          @RequestParam String title, @RequestParam String author, @RequestParam Integer categoryId,
                                          @RequestParam String publicationDate, @RequestParam Integer pages, @RequestParam Integer price,
                                          @RequestParam String imageUrl, @RequestParam String description) {
        System.out.println("PÓTTTTTTTTTTTTTTTTTTTTTT");
       Book book = createBook(id, title, author, categoryId, publicationDate, pages, price, imageUrl, description);
        if (bookServiceImpl.checkDuplicated(book)) {
            return ResponseEntity.badRequest().body("Sách đã tồn tại");
        }
        if (image != null) {
            String fileName = fileService.saveFile(image, imageDirectory);
            if (fileName.equals("error")) {
                return ResponseEntity.badRequest().body("File không đúng định dạng!");
            }
            book.setImageUrl("/static/book-covers/" + fileName);
        }
        bookServiceImpl.addBook(book);
        System.out.println(book.getImageUrl());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/book/delete/{bID}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer bID) {
        bookServiceImpl.deleteBook(bID);
        return ResponseEntity.ok().build();
    }
}
