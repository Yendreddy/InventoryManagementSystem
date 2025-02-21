package org.project.InventoryManagementSystem.service;

import org.project.InventoryManagementSystem.entity.OrdersPlaced;

import java.util.List;
import java.util.UUID;

public interface OrdersPlacedService {
    List<OrdersPlaced> getAllOrders();

    OrdersPlaced saveOrder(OrdersPlaced order);

    OrdersPlaced updateOrderById(UUID order_id, OrdersPlaced orders);

    void deleteOrderById(UUID order_id);

    OrdersPlaced getOrderById(UUID order_id);
}
