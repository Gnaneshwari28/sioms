package com.sioms.inventory.controller;

import com.sioms.inventory.entity.Category;
import com.sioms.inventory.entity.Product;
import com.sioms.inventory.repository.CategoryRepository;
import com.sioms.inventory.requestDTO.ProductRequestDTO;
import com.sioms.inventory.service.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {
    private final ProductServiceImpl productService;

    @Autowired
    private CategoryRepository categoryRepository;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }


    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody ProductRequestDTO request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();
        product.setPrice(request.getPrice());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setCategory(category);
        product.setQuantity(request.getQuantity());

        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(savedProduct);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO requestDTO) {
        try {
            Product product = productService.getProductById(id);
            Category category = categoryRepository.findById(requestDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setName(requestDTO.getName());
            product.setPrice(requestDTO.getPrice());
            product.setCategory(category);
            product.setQuantity(requestDTO.getQuantity());
            product.setDescription(requestDTO.getDescription());
            Product updated = productService.updateProduct(id, product);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
