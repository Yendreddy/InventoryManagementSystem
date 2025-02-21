package org.project.InventoryManagementSystem.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project.InventoryManagementSystem.entity.OrdersPlaced;
import org.project.InventoryManagementSystem.entity.Product;
import org.project.InventoryManagementSystem.exception.OrderNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrdersPlacedServiceImplTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Transaction transaction;

    @InjectMocks
    private OrdersPlacedServiceImpl ordersPlacedService;

    private OrdersPlaced order;
    private Product product;

    @BeforeEach
    public void setUp() {
        order = OrdersPlaced.builder()
                .order_id(UUID.randomUUID())
                .quantity(5)
                .product_id(UUID.randomUUID())
                .build();

        product = Product.builder()
                .product_id(order.getProduct_id())
                .price(100)
                .quantity(10)
                .build();

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
    }

    @Test
    public void testGetAllOrders() {
        when(session.createQuery("FROM OrdersPlaced", OrdersPlaced.class).list()).thenReturn(Collections.singletonList(order));

        List<OrdersPlaced> orders = ordersPlacedService.getAllOrders();

        assertNotNull(orders);
        assertEquals(1, orders.size());
        Mockito.verify(session, times(1)).createQuery("FROM OrdersPlaced", OrdersPlaced.class);
    }

    @Test
    public void testSaveOrder() {
        when(session.get(Product.class, order.getProduct_id())).thenReturn(product);

        OrdersPlaced savedOrder = ordersPlacedService.saveOrder(order);

        assertNotNull(savedOrder);
        Mockito.verify(session, times(1)).save(order);
        Mockito.verify(session, times(1)).merge(product);
    }

    @Test
    public void testUpdateOrderById() {
        when(session.get(OrdersPlaced.class, order.getOrder_id())).thenReturn(order);

        OrdersPlaced updatedOrder = ordersPlacedService.updateOrderById(order.getOrder_id(), order);

        assertNotNull(updatedOrder);
        Mockito.verify(session, times(1)).merge(order);
    }

    @Test
    public void testDeleteOrderById() {
        when(session.get(OrdersPlaced.class, order.getOrder_id())).thenReturn(order);

        ordersPlacedService.deleteOrderById(order.getOrder_id());

        Mockito.verify(session, times(1)).delete(order);
    }

    @Test
    public void testGetOrderById() {
        when(session.get(OrdersPlaced.class, order.getOrder_id())).thenReturn(order);

        OrdersPlaced foundOrder = ordersPlacedService.getOrderById(order.getOrder_id());

        assertNotNull(foundOrder);
        Mockito.verify(session, times(1)).get(OrdersPlaced.class, order.getOrder_id());
    }

    @Test
    public void testOrderNotFoundException() {
        when(session.get(OrdersPlaced.class, order.getOrder_id())).thenReturn(null);

        assertThrows(OrderNotFoundException.class, () -> ordersPlacedService.getOrderById(order.getOrder_id()));
    }
}