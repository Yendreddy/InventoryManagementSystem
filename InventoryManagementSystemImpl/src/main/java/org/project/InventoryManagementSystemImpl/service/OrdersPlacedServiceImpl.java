package org.project.InventoryManagementSystemImpl.service;

import org.project.InventoryManagementSystemImpl.entity.OrdersPlaced;
import org.project.InventoryManagementSystemImpl.entity.Product;
import org.project.InventoryManagementSystemImpl.exception.OrderNotFoundException;
import org.project.InventoryManagementSystemImpl.exception.ProductNotFoundException;
import org.project.InventoryManagementSystemImpl.repository.OrdersPlacedRepository;
import org.project.InventoryManagementSystemImpl.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrdersPlacedServiceImpl implements OrdersPlacedService {

    @Autowired
    private OrdersPlacedRepository ordersPlacedRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<OrdersPlaced> getAllOrders() {
        return ordersPlacedRepository.findAll();
    }

    @Override
    public OrdersPlaced saveOrder(OrdersPlaced order) {
        order.calculateTotalPrice();
        OrdersPlaced savedOrder = ordersPlacedRepository.save(order);
        Product product = productRepository.findById(order.getProduct_id())
                .orElseThrow(() -> new ProductNotFoundException("Product not found" + order.getProduct_id()));
        product.setQuantity(product.getQuantity() - order.getQuantity());
        productRepository.save(product);
        return savedOrder;
    }

    @Override
    public OrdersPlaced updateOrderById(UUID order_id, OrdersPlaced orders) {
        return ordersPlacedRepository.findById(order_id)
                .map(existingOrder -> {
                    existingOrder.setTotal_price(orders.getTotal_price());
                    return ordersPlacedRepository.save(existingOrder);
                })
                .orElseThrow(() -> new OrderNotFoundException("Order not found" + order_id));
    }

    @Override
    public void deleteOrderById(UUID order_id) {
        OrdersPlaced ordersPlaced = ordersPlacedRepository.findById(order_id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found" + order_id));
        ordersPlacedRepository.delete(ordersPlaced);
    }

    @Override
    public OrdersPlaced getOrderById(UUID order_id) {
        return ordersPlacedRepository.findById(order_id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found" + order_id));
    }
}