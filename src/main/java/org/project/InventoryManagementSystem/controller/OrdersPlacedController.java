package org.project.InventoryManagementSystem.controller;

import org.project.InventoryManagementSystem.entity.OrdersPlaced;
import org.project.InventoryManagementSystem.service.OrdersPlacedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrdersPlacedController {

    @Autowired
    private OrdersPlacedService ordersPlacedService;

    @GetMapping("/orders")
    public List<OrdersPlaced> getAllOrders() {
        return ordersPlacedService.getAllOrders();
    }

//    @GetMapping("/{id}")
//    public OrdersPlaced getOrderById(@PathVariable UUID id) {
//        return ordersPlacedService.getOrderById(id);
//    }

    @PostMapping("/orders")
    public OrdersPlaced saveOrder(@RequestBody OrdersPlaced order) {
        return ordersPlacedService.saveOrder(order);
    }

    @PutMapping("/orders/{id}")
    public OrdersPlaced updateOrderById(@PathVariable("id") UUID orderId, @RequestBody OrdersPlaced orders) {
        return ordersPlacedService.updateOrderById(orderId, orders);
    }

    @DeleteMapping("/orders/{id}")
    public void deleteOrderById(@PathVariable("id") UUID orderId) {
        ordersPlacedService.deleteOrderById(orderId);
    }
}
