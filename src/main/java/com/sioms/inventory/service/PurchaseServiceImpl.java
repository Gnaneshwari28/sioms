package com.sioms.inventory.service;

import com.sioms.inventory.entity.Product;
import com.sioms.inventory.entity.Purchase;
import com.sioms.inventory.entity.Supplier;
import com.sioms.inventory.repository.ProductRepository;
import com.sioms.inventory.repository.PurchaseRepository;
import com.sioms.inventory.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Autowired
    private  ProductRepository productRepository;

    @Autowired
    private  SupplierRepository supplierRepository;

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
        if (purchase.getProduct() == null || purchase.getSupplier() == null) {
            throw new IllegalArgumentException("Product or Supplier cannot be null");
        }
        Product product = productRepository.findById(purchase.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Supplier supplier = supplierRepository.findById(purchase.getSupplier().getId())
                .orElseThrow(()-> new RuntimeException("Supplier not found"));
        Purchase existing = getPurchaseById(id);
        existing.setProduct(product);
        existing.setSupplier(supplier);
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
