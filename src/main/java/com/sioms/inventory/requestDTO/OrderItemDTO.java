package com.sioms.inventory.requestDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDTO {
    private Long productId;
    private Integer quantity;
    private Double price;
}

