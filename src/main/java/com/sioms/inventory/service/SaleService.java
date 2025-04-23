package com.sioms.inventory.service;

import com.sioms.inventory.entity.Sale;

import java.util.List;

public interface SaleService {
    Sale saveSale(Sale sale);
    List<Sale> getAllSales();
    Sale getSaleById(Long id);
    Sale updateSale(Long id, Sale sale);
    void deleteSale(Long id);
}
