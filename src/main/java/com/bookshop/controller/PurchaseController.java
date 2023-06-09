package com.bookshop.controller;

import com.bookshop.DTO.Purchase;
import com.bookshop.DTO.User;
import com.bookshop.service.Impl.PurchaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/")
public class PurchaseController {
    @Autowired
    private PurchaseServiceImpl purchaseService;

    @PostMapping("/cart_quantity")
    public int quantityLines(@RequestBody User user) {
        return purchaseService.getUserCart(user).size();
    }
    @PostMapping("/total_money")
    public int totalMoney(@RequestBody User user) {
        int sum = 0;
        for(Purchase x : purchaseService.getUserCart(user)) {
            sum += x.getQuantity() * x.getBook().getPrice();
        }
        return sum;
    }

    @PostMapping("/cart")
    public List<Purchase> getUserCart(@RequestBody User user) {
        return purchaseService.getUserCart(user);
    }
    @PostMapping("/order")
    public List<Purchase> getUserPurchase(@RequestBody User user) {
        return purchaseService.getUserPurchase(user);
    }
    @PostMapping("/addtocart")
    public ResponseEntity<Purchase> addToCart(@RequestBody Purchase purchase) {
        Purchase exist = purchaseService.checkInCart(purchase);
        if(exist != null) {
            purchase.setId(exist.getId());
        }
        Purchase response = purchaseService.addPurchase(purchase);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/confirm")
    public ResponseEntity<Purchase> confirm(@RequestBody Purchase purchase) {
        Purchase response = purchaseService.updatePurchase(purchase);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/cart/delete/{pid}")
    public void deleteCartItem(@PathVariable int pid) {
        System.out.println(pid);
        purchaseService.deleteCartItem(pid);
    }

    @PostMapping("/purchase")
    public ResponseEntity<String> makePurchase(@RequestBody List<Purchase> purchases) {
        for(Purchase purchase : purchases) {
            purchaseService.updatePurchase(purchase);
        }
        return ResponseEntity.ok().build();
    }
}
