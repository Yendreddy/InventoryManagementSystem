package org.project.InventoryManagementSystem.service;

import org.project.InventoryManagementSystem.dto.CategoryDTO;
import org.project.InventoryManagementSystem.dto.CustomerDTO;
import org.project.InventoryManagementSystem.dto.OrdersPlacedDTO;
import org.project.InventoryManagementSystem.exception.CustomerNotFoundException;
import org.project.InventoryManagementSystem.exception.OrderNotFoundException;

import java.util.List;
import java.util.UUID;

public interface OrdersPlacedService {

    /**
     * Fetches the list of all orders.
     *
     * @return A list of {@link OrdersPlacedDTO} objects representing all orders.
     */
    List<OrdersPlacedDTO> getAllOrders();

    /**
     * Finds an order by its ID.
     *
     * @param order_id The UUID of the order to find.
     * @return A {@link OrdersPlacedDTO} object representing the found order.
     * @throws OrderNotFoundException if the order is not found.
     */
    OrdersPlacedDTO getOrderById(UUID order_id);

    /**
     * Saves a new order.
     *
     * @param ordersPlacedDTO The {@link OrdersPlacedDTO} object representing the order to save.
     * @return true if the order is saved successfully, false otherwise.
     */
    boolean saveOrder(OrdersPlacedDTO ordersPlacedDTO);

    /**
     * Updates an existing order by its ID.
     *
     * @param order_id The UUID of the order to update.
     * The {@link OrdersPlacedDTO} object representing the updated order.
     * @return true if the order is updated successfully, false otherwise.
     * @throws OrderNotFoundException if the customer is not found.
     */
    boolean updateOrderById(UUID order_id, OrdersPlacedDTO ordersPlacedDTO);

    /**
     * Deletes an order by its ID.
     *
     * @param order_id The UUID of the order to delete.
     * @return true if the order is deleted successfully, false otherwise.
     * @throws OrderNotFoundException if the customer is not found.
     */
    boolean deleteOrderById(UUID order_id);
}
