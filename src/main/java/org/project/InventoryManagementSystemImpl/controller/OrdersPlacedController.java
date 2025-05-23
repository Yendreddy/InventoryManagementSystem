package org.project.InventoryManagementSystemImpl.controller;

import org.modelmapper.ModelMapper;
import org.project.InventoryManagementSystemImpl.dto.OrdersPlacedDTO;
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



    @GetMapping
    public List<OrdersPlacedDTO> getAllOrders() {
        return ordersPlacedService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersPlacedDTO> getOrderById(@PathVariable("id") UUID order_id) {
        OrdersPlacedDTO ordersPlacedDTO = ordersPlacedService.getOrderById(order_id);
        return ResponseEntity.ok().body(ordersPlacedDTO);
    }

    @PostMapping
    public ResponseEntity<OrdersPlacedDTO> saveOrder(@RequestBody OrdersPlacedDTO ordersPlacedDTO) {
        boolean isCreated = ordersPlacedService.saveOrder(ordersPlacedDTO);
        if (isCreated) {
            return new ResponseEntity<>(ordersPlacedDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdersPlacedDTO> updateOrderById(@PathVariable("id") UUID order_id, @RequestBody OrdersPlacedDTO ordersPlacedDTO) {
        boolean isUpdated = ordersPlacedService.updateOrderById(order_id, ordersPlacedDTO);
        if (isUpdated) {
            return ResponseEntity.ok().body(ordersPlacedDTO);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable("id") UUID order_id) {
        boolean isDeleted = ordersPlacedService.deleteOrderById(order_id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}