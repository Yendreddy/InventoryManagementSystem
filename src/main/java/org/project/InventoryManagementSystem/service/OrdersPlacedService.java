package org.project.InventoryManagementSystem.service;

import org.project.InventoryManagementSystem.dto.OrdersPlacedDTO;

import java.util.List;
import java.util.UUID;

public interface OrdersPlacedService {
    List<OrdersPlacedDTO> getAllOrders();
    OrdersPlacedDTO getOrderById(UUID order_id);
    boolean saveOrder(OrdersPlacedDTO ordersPlacedDTO);
    boolean updateOrderById(UUID order_id, OrdersPlacedDTO ordersPlacedDTO);
    boolean deleteOrderById(UUID order_id);
}
