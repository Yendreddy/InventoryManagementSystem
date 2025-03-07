package org.project.InventoryManagementSystem.controller;

import java.util.List;
import java.util.UUID;
import org.project.InventoryManagementSystem.entity.OrdersPlaced;
import org.project.InventoryManagementSystem.service.OrdersPlacedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/orders"})
public class OrdersPlacedController {
    @Autowired
    private OrdersPlacedService ordersPlacedService;

    public OrdersPlacedController() {
    }

    @GetMapping({"/get"})
    public List<OrdersPlaced> getAllOrders() {
        return this.ordersPlacedService.getAllOrders();
    }

    @GetMapping({"/get/{order_id}"})
    public ResponseEntity<OrdersPlaced> getOrderById(@PathVariable("order_id") UUID order_id) {
        OrdersPlaced order = this.ordersPlacedService.getOrderById(order_id);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping({"/save"})
    public ResponseEntity<OrdersPlaced> saveOrder(@RequestBody OrdersPlaced order) {
        OrdersPlaced savedOrder = this.ordersPlacedService.saveOrder(order);
        return new ResponseEntity(savedOrder, HttpStatus.CREATED);
    }

    @PutMapping({"/update/{order_id}"})
    public ResponseEntity<OrdersPlaced> updateOrderById(@PathVariable("order_id") UUID order_id, @RequestBody OrdersPlaced order) {
        OrdersPlaced updatedOrder = this.ordersPlacedService.updateOrderById(order_id, order);
        return ResponseEntity.ok().body(updatedOrder);
    }

    @DeleteMapping({"/delete/{order_id}"})
    public ResponseEntity<Void> deleteOrderById(@PathVariable("order_id") UUID order_id) {
        this.ordersPlacedService.deleteOrderById(order_id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
