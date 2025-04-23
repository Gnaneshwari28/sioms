package com.sioms.inventory.repository;

import com.sioms.inventory.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

    boolean existsByProduct_Id(Long productId);


}
