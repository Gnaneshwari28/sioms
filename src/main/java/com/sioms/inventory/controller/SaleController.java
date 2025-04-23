package com.sioms.inventory.controller;

import com.sioms.inventory.entity.Product;
import com.sioms.inventory.entity.Sale;
import com.sioms.inventory.repository.ProductRepository;
import com.sioms.inventory.service.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin("*")
public class SaleController {

    private final SaleService saleService;
    private final ProductRepository productRepository;

    public SaleController(SaleService saleService, ProductRepository productRepository) {
        this.saleService = saleService;
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    @GetMapping("/{id}")
    public Sale getSaleById(@PathVariable Long id) {
        return saleService.getSaleById(id);
    }

    @PostMapping
    public ResponseEntity<Sale> saveSale(@RequestBody Sale request) {
        Product product = productRepository.findById(request.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        request.setProduct(product);

        if (request.getSaleDate() == null) {
            request.setSaleDate(LocalDate.now());
        }

        Sale saved = saleService.saveSale(request);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sale> updateSale(@PathVariable Long id, @RequestBody Sale request) {
        return ResponseEntity.ok(saleService.updateSale(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }
}
