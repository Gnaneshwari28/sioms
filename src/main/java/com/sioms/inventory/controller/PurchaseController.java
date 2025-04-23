package com.sioms.inventory.controller;

import com.sioms.inventory.entity.Product;
import com.sioms.inventory.entity.Purchase;
import com.sioms.inventory.entity.Supplier;
import com.sioms.inventory.repository.ProductRepository;
import com.sioms.inventory.repository.SupplierRepository;
import com.sioms.inventory.service.PurchaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/purchases")
@CrossOrigin("*")
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    public PurchaseController(PurchaseService purchaseService, ProductRepository productRepository, SupplierRepository supplierRepository) {
        this.purchaseService = purchaseService;
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    @GetMapping
    public List<Purchase> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    @GetMapping("/{id}")
    public Purchase getPurchaseById(@PathVariable Long id) {
        return purchaseService.getPurchaseById(id);
    }

    @PostMapping
    public ResponseEntity<Purchase> savePurchase(@RequestBody Purchase request) {
        Product product = productRepository.findById(request.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Supplier supplier = supplierRepository.findById(request.getSupplier().getId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        request.setProduct(product);
        request.setSupplier(supplier);

        // If no date provided, use current
        if (request.getPurchaseDate() == null) {
            request.setPurchaseDate(LocalDate.now());
        }

        return ResponseEntity.ok(purchaseService.savePurchase(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Purchase> updatePurchase(@PathVariable Long id, @RequestBody Purchase request) {
        return ResponseEntity.ok(purchaseService.updatePurchase(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePurchase(@PathVariable Long id) {
        purchaseService.deletePurchase(id);
        return ResponseEntity.noContent().build();
    }
}
