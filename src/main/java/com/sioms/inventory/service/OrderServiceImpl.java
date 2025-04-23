package com.sioms.inventory.service;

import com.sioms.inventory.entity.Customer;
import com.sioms.inventory.entity.Order;
import com.sioms.inventory.entity.OrderItem;
import com.sioms.inventory.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order placeOrder(Order order) {
        order.setOrderDate(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    @Transactional
    public Order updateOrder(Long id, Order updatedOrder) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Customer customer = updatedOrder.getCustomer();
        if (customer != null) {
            existingOrder.setCustomer(customer);
        }

        existingOrder.getOrderItems().clear();

        existingOrder.setTotalAmount(updatedOrder.getTotalAmount());
        existingOrder.setOrderDate(LocalDateTime.now());

        for (OrderItem item : updatedOrder.getOrderItems()) {
            item.setOrder(existingOrder);
            existingOrder.getOrderItems().add(item);
        }

        return orderRepository.save(existingOrder);
    }


    @Override
    @Transactional
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(order);
    }

}
