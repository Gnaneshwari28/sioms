package com.sioms.inventory.service;

import com.sioms.inventory.entity.Purchase;
import com.sioms.inventory.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public Purchase savePurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    @Override
    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    @Override
    public Purchase getPurchaseById(Long id) {
        return purchaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase not found"));
    }

    @Override
    public Purchase updatePurchase(Long id, Purchase purchase) {
        Purchase existing = getPurchaseById(id);
        existing.setProduct(purchase.getProduct());
        existing.setSupplier(purchase.getSupplier());
        existing.setQuantity(purchase.getQuantity());
        existing.setPricePerUnit(purchase.getPricePerUnit());
        existing.setPurchaseDate(purchase.getPurchaseDate());
        return purchaseRepository.save(existing);
    }

    @Override
    public void deletePurchase(Long id) {
        purchaseRepository.deleteById(id);
    }
}
