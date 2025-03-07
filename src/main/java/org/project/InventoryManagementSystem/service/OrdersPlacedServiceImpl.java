package org.project.InventoryManagementSystem.service;

import java.util.List;
import java.util.UUID;
import org.project.InventoryManagementSystem.entity.OrdersPlaced;
import org.project.InventoryManagementSystem.exception.OrderNotFoundException;
import org.project.InventoryManagementSystem.repository.OrdersPlacedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class OrdersPlacedServiceImpl implements OrdersPlacedService {
    @Autowired
    private OrdersPlacedRepository ordersPlacedRepository;

    public OrdersPlacedServiceImpl() {
    }

    @Cacheable({"orders"})
    public List<OrdersPlaced> getAllOrders() {
        return this.ordersPlacedRepository.findAll();
    }

    @Cacheable(
            value = {"orders"},
            key = "#order_id"
    )
    public OrdersPlaced getOrderById(UUID order_id) {
        return (OrdersPlaced)this.ordersPlacedRepository.findById(order_id).orElseThrow(() -> new OrderNotFoundException("Order", "id", order_id));
    }

    @CacheEvict(
            value = {"orders"},
            allEntries = true
    )
    public OrdersPlaced saveOrder(OrdersPlaced order) {
        return (OrdersPlaced)this.ordersPlacedRepository.save(order);
    }

    @CacheEvict(
            value = {"orders"},
            key = "#order_id"
    )
    public OrdersPlaced updateOrderById(UUID order_id, OrdersPlaced order) {
        OrdersPlaced existingOrder = (OrdersPlaced)this.ordersPlacedRepository.findById(order_id).orElseThrow(() -> new OrderNotFoundException("Order", "id", order_id));
        existingOrder.setQuantity(order.getQuantity());
        existingOrder.setTotal_price(order.getTotal_price());
        existingOrder.setCustomer(order.getCustomer());
        existingOrder.setProduct_id(order.getProduct_id());
        existingOrder.setCategory_id(order.getCategory_id());
        return (OrdersPlaced)this.ordersPlacedRepository.save(existingOrder);
    }
    @CacheEvict(
            value = {"orders"},
            key = "#order_id"
    )
    public void deleteOrderById(UUID order_id) {
        OrdersPlaced order = (OrdersPlaced)this.ordersPlacedRepository.findById(order_id).orElseThrow(() -> new OrderNotFoundException("Order", "id", order_id));
        this.ordersPlacedRepository.delete(order);
    }
}
