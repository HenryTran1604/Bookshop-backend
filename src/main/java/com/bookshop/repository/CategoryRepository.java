package com.bookshop.repository;

import com.bookshop.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    @Query(value = "SELECT * FROM category WHERE category_name = ?1 AND id != ?2", nativeQuery = true)
    CategoryEntity findByCategoryName(String name, int id);
}
