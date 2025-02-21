package org.project.InventoryManagementSystem.controller;

import org.project.InventoryManagementSystem.entity.OrdersPlaced;
import org.project.InventoryManagementSystem.service.OrdersPlacedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class OrdersPlacedController {

    @Autowired
    private OrdersPlacedService ordersPlacedService;

    @GetMapping("/ordersPlaced")
    public List<OrdersPlaced> getAllOrders() {
        return ordersPlacedService.getAllOrders();
    }

    @GetMapping("/ordersPlaced/{id}")
    public OrdersPlaced getOrderById(@PathVariable("id") UUID order_id) {
        return ordersPlacedService.getOrderById(order_id);
    }

    @PostMapping("/ordersPlaced")
    public OrdersPlaced saveOrder(@RequestBody OrdersPlaced order) {
        return ordersPlacedService.saveOrder(order);
    }

    @PutMapping("/ordersPlaced/{id}")
    public OrdersPlaced updateOrderById(@PathVariable("id") UUID order_id, @RequestBody OrdersPlaced orders) {
        return ordersPlacedService.updateOrderById(order_id, orders);
    }

    @DeleteMapping("/ordersPlaced/{id}")
    public void deleteOrderById(@PathVariable("id") UUID order_id) {
        ordersPlacedService.deleteOrderById(order_id);
    }
}
