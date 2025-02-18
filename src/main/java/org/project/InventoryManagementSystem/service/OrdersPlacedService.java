package org.project.InventoryManagementSystem.service;

import org.project.InventoryManagementSystem.entity.OrdersPlaced;

import java.util.List;
import java.util.UUID;

public interface OrdersPlacedService {
    List<OrdersPlaced> getAllOrders();

    OrdersPlaced saveOrder(OrdersPlaced order);

    OrdersPlaced updateOrderById(UUID orderId, OrdersPlaced orders);

    void deleteOrderById(UUID orderId);
}
