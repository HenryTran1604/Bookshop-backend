package com.bookshop.service;

import com.bookshop.DTO.Purchase;
import com.bookshop.DTO.User;

import java.util.List;

public interface IPurchaseService {
    Purchase checkInCart(Purchase purchase);
    List<Purchase> getUserCart(User user);
    List<Purchase> getUserPurchase(User user);
    Purchase addPurchase(Purchase purchase);
    Purchase updatePurchase(Purchase purchase);
    void deleteCartItem(int purchaseId);
}
