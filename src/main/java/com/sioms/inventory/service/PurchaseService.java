package com.sioms.inventory.service;

import com.sioms.inventory.entity.Purchase;
import java.util.List;

public interface PurchaseService {
    Purchase savePurchase(Purchase purchase);
    List<Purchase> getAllPurchases();
    Purchase getPurchaseById(Long id);
    Purchase updatePurchase(Long id, Purchase purchase);
    void deletePurchase(Long id);
}
