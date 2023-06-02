package com.bookshop.converter;

import com.bookshop.DTO.Purchase;
import com.bookshop.entity.PurchaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PurchaseConverter {
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private PurchaseDetailConverter purchaseDetailConverter;
    public PurchaseEntity toEntity(Purchase dto) {
        PurchaseEntity entity = new PurchaseEntity();
        entity.setId(dto.getId());
        entity.setUser(userConverter.toEntity(dto.getUser()));
        entity.setPurchaseDate(dto.getPurchaseDate());
        return entity;
    }
    public Purchase toDto(PurchaseEntity entity) {
        Purchase dto = new Purchase();
        dto.setId(entity.getId());
        dto.setUser(userConverter.toDto(entity.getUser()));
        dto.setPurchaseDate(entity.getPurchaseDate());
        dto.setPurchaseStatus(entity.getPurchaseStatus());
        return dto;
    }
}
