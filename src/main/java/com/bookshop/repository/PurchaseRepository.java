package com.bookshop.repository;

import com.bookshop.DTO.Book;
import com.bookshop.DTO.User;
import com.bookshop.entity.BookEntity;
import com.bookshop.entity.PurchaseEntity;
import com.bookshop.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Integer> {
    PurchaseEntity findByUserAndPurchaseStatusAndBook(UserEntity user, String status, BookEntity book);
    List<PurchaseEntity> findByUserAndPurchaseStatus(UserEntity user, String purchaseStatus);
    List<PurchaseEntity> findByUserAndPurchaseStatusIn(UserEntity user, List<String> statuses);
}
