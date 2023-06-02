package com.bookshop.converter;

import com.bookshop.DTO.PurchaseDetail;
import com.bookshop.entity.PurchaseDetailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PurchaseDetailConverter {
    @Autowired
    private PurchaseConverter purchaseConverter;
    @Autowired
    private BookConverter bookConverter;
    public PurchaseDetailEntity toEntity(PurchaseDetail dto) {
        PurchaseDetailEntity entity = new PurchaseDetailEntity();
        entity.setPurchase(purchaseConverter.toEntity(dto.getPurchase()));
        return entity;
    }
    public PurchaseDetail toDto(PurchaseDetailEntity entity) {
        PurchaseDetail dto = new PurchaseDetail();
        dto.setId(entity.getId());
        dto.setPurchase(purchaseConverter.toDto(entity.getPurchase()));
        dto.setQuantity(entity.getQuantity());
        dto.setBook(bookConverter.toDto(entity.getBook()));
        return dto;
    }
}
