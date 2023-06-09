package com.bookshop.service.Impl;

import com.bookshop.DTO.Purchase;
import com.bookshop.DTO.User;
import com.bookshop.converter.BookConverter;
import com.bookshop.converter.PurchaseConverter;
import com.bookshop.converter.UserConverter;
import com.bookshop.entity.PurchaseEntity;
import com.bookshop.repository.PurchaseRepository;
import com.bookshop.service.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class PurchaseServiceImpl implements IPurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private PurchaseConverter purchaseConverter;
    @Autowired
    private BookConverter bookConverter;
    @Autowired
    private UserConverter userConverter;


    @Override
    public Purchase checkInCart(Purchase purchase) {
        PurchaseEntity entity = purchaseRepository.findByUserAndPurchaseStatusAndBook(userConverter.toEntity(purchase.getUser()), "in-cart", bookConverter.toEntity(purchase.getBook()));
        System.out.println(entity);
        if(entity != null ) {
            Purchase response = purchaseConverter.toDto(entity);
            return response;
        }
        return null;
    }

    @Override
    public List<Purchase> getUserCart(User user) {
        System.out.println(user.getUsername());
        List<PurchaseEntity> entities = purchaseRepository.findByUserAndPurchaseStatus(userConverter.toEntity(user), "in-cart");
        List<Purchase> responses = entities.stream().map(purchaseConverter::toDto).toList();
        return responses;
    }

    @Override
    public List<Purchase> getUserPurchase(User user) {
        List<PurchaseEntity> entities = purchaseRepository.findByUserAndPurchaseStatusIn(userConverter.toEntity(user), Arrays.asList("delivery", "completed"));
        List<Purchase> responses = entities.stream().map(purchaseConverter::toDto).toList();
        return responses;
    }

    @Override
    public Purchase addPurchase(Purchase purchase) {
        PurchaseEntity entity = purchaseConverter.toEntity(purchase);
        Purchase response = purchaseConverter.toDto(purchaseRepository.save(entity));
        return response;
    }

    @Override
    public Purchase updatePurchase(Purchase purchase) {
        PurchaseEntity entity = purchaseConverter.toEntity(purchase);
        Purchase response = purchaseConverter.toDto(purchaseRepository.save(entity));
        return response;
    }

    @Override
    public void deleteCartItem(int purchaseId) {
        purchaseRepository.deleteById(purchaseId);
    }
}
