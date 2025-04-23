package com.sioms.inventory.repository;

import com.sioms.inventory.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    boolean existsByCustomer_Id(Long customerId);


}

