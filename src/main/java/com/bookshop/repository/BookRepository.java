package com.bookshop.repository;

import com.bookshop.entity.BookEntity;
import com.bookshop.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    @Query(value = "SELECT * FROM book WHERE title LIKE %?1%", nativeQuery = true)
    List<BookEntity> findByTitleContaining(String title);
    @Query(value = "SELECT * FROM book WHERE title = ?1 AND author = ?2 AND id != ?3", nativeQuery = true)
    BookEntity findByTitleAndAuthor(String title, String author, int bookId);
    @Query(value = "SELECT author FROM book", nativeQuery = true)
    Set<String> findAllAuthor();
    List<BookEntity> findAllByAuthor(String author);

}
