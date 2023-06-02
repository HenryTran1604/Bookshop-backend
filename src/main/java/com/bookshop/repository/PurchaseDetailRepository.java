package com.bookshop.repository;

import com.bookshop.entity.PurchaseDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetailEntity, Integer> {
}
