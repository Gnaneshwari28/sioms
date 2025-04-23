package com.sioms.inventory.service;

import com.sioms.inventory.entity.Product;
import com.sioms.inventory.entity.Sale;
import com.sioms.inventory.repository.ProductRepository;
import com.sioms.inventory.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;

    public SaleServiceImpl(SaleRepository saleRepository, ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Sale saveSale(Sale sale) {
        Product product = productRepository.findById(sale.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getQuantity() < sale.getQuantity()) {
            throw new RuntimeException("Insufficient stock available");
        }

        // Reduce stock
        product.setQuantity(product.getQuantity() - sale.getQuantity());
        productRepository.save(product);

        // Set current date if not provided
        if (sale.getSaleDate() == null) {
            sale.setSaleDate(LocalDate.now());
        }

        return saleRepository.save(sale);
    }

    @Override
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    @Override
    public Sale getSaleById(Long id) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found"));
    }

    @Override
    public Sale updateSale(Long id, Sale updated) {
        Sale existing = getSaleById(id);
        existing.setQuantity(updated.getQuantity());
        existing.setSellingPricePerUnit(updated.getSellingPricePerUnit());
        existing.setSaleDate(updated.getSaleDate());
        return saleRepository.save(existing);
    }

    @Override
    public void deleteSale(Long id) {
        saleRepository.deleteById(id);
    }
}
