package com.sioms.inventory.service;

import com.sioms.inventory.entity.Inventory;
import java.util.List;

public interface InventoryService {
    Inventory saveInventory(Inventory inventory);
    List<Inventory> getAllInventory();
    Inventory getInventoryById(Long id);
    Inventory updateInventory(Long id, Inventory inventory);
    void deleteInventory(Long id);
}
