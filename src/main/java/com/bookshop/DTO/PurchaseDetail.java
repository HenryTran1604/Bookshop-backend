package com.bookshop.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PurchaseDetail {
    private int id;
    private Purchase purchase;
    private Book book;
    private int quantity;
}
