package org.project.InventoryManagementSystem.service;

import org.project.InventoryManagementSystem.entity.OrdersPlaced;
import org.project.InventoryManagementSystem.exception.OrderNotFoundException;

import java.util.List;
import java.util.UUID;

public interface OrdersPlacedService {

    /**
     * Fetches the list of all orders.
     *
     * @return A list of {@link OrdersPlaced} objects representing all orders.
     */
    List<OrdersPlaced> getAllOrders();

    /**
     * Finds an order by its ID.
     *
     * @param order_id The UUID of the order to find.
     * @return A {@link OrdersPlaced} object representing the found order.
     * @throws OrderNotFoundException if the order is not found.
     */
    OrdersPlaced getOrderById(UUID order_id);

    /**
     * Saves a new order.
     *
     * @param order The {@link OrdersPlaced} object representing the order to save.
     * @return order.
     */
    OrdersPlaced saveOrder(OrdersPlaced order);

    /**
     * Updates an existing order by its ID.
     *
     * @param order_id The UUID of the order to update.
     * The {@link OrdersPlaced} object representing the updated order.
     * @return updated order.
     * @throws OrderNotFoundException if the customer is not found.
     */
    OrdersPlaced updateOrderById(UUID order_id, OrdersPlaced order);

    /**
     * Deletes an order by its ID.
     *
     * @param order_id The UUID of the order to delete.
     * @throws OrderNotFoundException if the customer is not found.
     */
    void deleteOrderById(UUID order_id);
}

