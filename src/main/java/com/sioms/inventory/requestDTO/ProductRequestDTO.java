package com.sioms.inventory.requestDTO;

import com.sioms.inventory.entity.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {
    private String name;
    private String sku;
    private double price;
    private String description;
    private Long categoryId;
    private int quantity;


}
