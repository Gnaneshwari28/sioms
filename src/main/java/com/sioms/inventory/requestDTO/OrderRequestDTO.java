package com.sioms.inventory.requestDTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderRequestDTO {
    private Long customerId;

    private List<OrderItemDTO> items;
}
