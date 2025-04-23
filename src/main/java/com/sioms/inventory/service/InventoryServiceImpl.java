package com.sioms.inventory.service;

import com.sioms.inventory.entity.Inventory;
import com.sioms.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public Inventory saveInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory getInventoryById(Long id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    public List<Inventory> getInventoryByProductId(Long productId) {
        return inventoryRepository.findByProductId(productId);
    }


    @Override
    public Inventory updateInventory(Long id, Inventory inventory) {
        Inventory existing = inventoryRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setQuantity(inventory.getQuantity());
            existing.setProductId(inventory.getProductId());
            existing.setRemarks(inventory.getRemarks());
            existing.setStatus(inventory.getStatus());
            existing.setBatchNo(inventory.getBatchNo());
            existing.setLocation(inventory.getLocation());
            existing.setStatus(inventory.getStatus());
            return inventoryRepository.save(existing);
        }
        return null;
    }

    @Override
    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }
}
