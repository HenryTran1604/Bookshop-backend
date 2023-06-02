package com.bookshop.controller;

import com.bookshop.DTO.Book;

import com.bookshop.service.Impl.BookServiceImpl;
import com.bookshop.service.Impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/api/")

public class BookController {
    @Autowired
    private BookServiceImpl bookServiceImpl;

    @Autowired
    private CategoryServiceImpl categoryService;

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
        Set<String> authors = bookServiceImpl.getAllAuthors();
        return authors;
    }
    @GetMapping("/books")
    public List<Book> getAllBooks(){
        List<Book> books = bookServiceImpl.getAllBooks();
        System.out.println(books);
        return books;
    }
    @GetMapping("/book/{bID}")
    public Book getBook(@PathVariable String bID) {
        Book book = bookServiceImpl.getBookByID(Integer.parseInt(bID));
        return book;
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
        Book book = createBook(id, title, author, categoryId, publicationDate, pages,price, imageUrl, description);
        if(bookServiceImpl.checkDuplicated(book)) {
            return ResponseEntity.badRequest().body("Sách đã tồn ta");
        }
        String respone = bookServiceImpl.saveFile(image, book);
        if(respone.equals("ok")) {
            bookServiceImpl.addBook(book);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body(respone);
    }
    @PostMapping("/book/save/{bID}")
    public ResponseEntity<String> addBook(@RequestParam(required = false) MultipartFile image, @PathVariable Integer bID, @RequestParam Integer id,
                                        @RequestParam String title, @RequestParam String author, @RequestParam Integer categoryId,
                                        @RequestParam String publicationDate, @RequestParam Integer pages, @RequestParam Integer price,
                                        @RequestParam String imageUrl, @RequestParam String description) {
        System.out.println("PÓTTTTTTTTTTTTTTTTTTTTTT");
        Book book = createBook(id, title, author, categoryId, publicationDate, pages,price, imageUrl, description);
        if(bookServiceImpl.checkDuplicated(book)) {
            System.out.println("sách đã tồn tại");
            return ResponseEntity.badRequest().body("Sách đã tồn tại!");
        }
        String respone = bookServiceImpl.saveFile(image, book);
        if(respone.equals("ok")) {
            bookServiceImpl.addBook(book);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body(respone);
    }
    @DeleteMapping("/book/delete/{bID}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer bID) {
        bookServiceImpl.deleteBook(bID);
        return ResponseEntity.ok().build();
    }
}
