package com.sioms.inventory.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventory")
@Getter
@Setter
public class Inventory {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private Long productId;

        private Integer quantity;

        private String location;

        private String batchNo;

        private String status;

        private String remarks;

}


