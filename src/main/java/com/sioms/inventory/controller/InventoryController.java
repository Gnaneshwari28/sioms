package com.sioms.inventory.controller;

import com.sioms.inventory.entity.Inventory;
import com.sioms.inventory.service.InventoryServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryServiceImpl inventoryService;

    public InventoryController(InventoryServiceImpl inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public Inventory save(@RequestBody Inventory inventory) {
        return inventoryService.saveInventory(inventory);
    }

    @GetMapping
    public List<Inventory> getAll() {
        return inventoryService.getAllInventory();
    }

    @GetMapping("/{id}")
    public Inventory getById(@PathVariable Long id) {
        return inventoryService.getInventoryById(id);
    }

    @GetMapping("/product/{productId}")
    public List<Inventory> getByProductId(@PathVariable Long productId) {
        return inventoryService.getInventoryByProductId(productId);
    }

    @PutMapping("/{id}")
    @PostMapping
    public Inventory update(@PathVariable Long id,@RequestBody Inventory inventory) {
        return inventoryService.updateInventory(id,inventory);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
    }
}

