package org.project.InventoryManagementSystemImpl.controller;

import org.project.InventoryManagementSystemImpl.entity.OrdersPlaced;
import org.project.InventoryManagementSystemImpl.service.OrdersPlacedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrdersPlacedController {

    @Autowired
    private OrdersPlacedService ordersPlacedService;

    @GetMapping("/get")
    public List<OrdersPlaced> getAllOrders() {
        return ordersPlacedService.getAllOrders();
    }

    @GetMapping("/get/{order_id}")
    public ResponseEntity<OrdersPlaced> getOrderById(@PathVariable("order_id") UUID order_id) {
        OrdersPlaced order = ordersPlacedService.getOrderById(order_id);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping("/save")
    public ResponseEntity<OrdersPlaced> saveOrder(@RequestBody OrdersPlaced order) {
        OrdersPlaced savedOrder = ordersPlacedService.saveOrder(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @PutMapping("/update/{order_id}")
    public ResponseEntity<OrdersPlaced> updateOrderById(@PathVariable("order_id") UUID order_id, @RequestBody OrdersPlaced order) {
        OrdersPlaced updatedOrder = ordersPlacedService.updateOrderById(order_id, order);
        return ResponseEntity.ok().body(updatedOrder);
    }

    @DeleteMapping("/delete/{order_id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable("order_id") UUID order_id) {
        ordersPlacedService.deleteOrderById(order_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}