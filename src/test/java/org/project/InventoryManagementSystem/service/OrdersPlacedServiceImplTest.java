package org.project.InventoryManagementSystem.service;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project.InventoryManagementSystem.dto.OrdersPlacedDTO;
import org.project.InventoryManagementSystem.entity.OrdersPlaced;
import org.project.InventoryManagementSystem.exception.OrderNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    @BeforeEach
    public void setUp() {
        order = OrdersPlaced.builder()
                .order_id(UUID.randomUUID())
                .quantity(2)
                .total_price(200)
                .build();

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
    }

    @Test
    public void testGetAllOrders() {
        when(session.createQuery("FROM OrdersPlaced", OrdersPlaced.class).list()).thenReturn(Collections.singletonList(order));

        List<OrdersPlacedDTO> orders = ordersPlacedService.getAllOrders();

        assertNotNull(orders);
        assertEquals(1, orders.size());
        verify(session, times(1)).createQuery("FROM OrdersPlaced", OrdersPlaced.class);
    }

    @Test
    public void testSaveOrder() {
        boolean isSaved = ordersPlacedService.saveOrder(OrdersPlacedDTO.builder().build());

        assertTrue(isSaved);
        verify(session, times(1)).save(any(OrdersPlaced.class));
    }

    @Test
    public void testUpdateOrderById() {
        when(session.get(OrdersPlaced.class, order.getOrder_id())).thenReturn(order);

        boolean isUpdated = ordersPlacedService.updateOrderById(order.getOrder_id(), OrdersPlacedDTO.builder().build());

        assertTrue(isUpdated);
        verify(session, times(1)).merge(any(OrdersPlaced.class));
    }

    @Test
    public void testDeleteOrderById() {
        when(session.get(OrdersPlaced.class, order.getOrder_id())).thenReturn(order);

        boolean isDeleted = ordersPlacedService.deleteOrderById(order.getOrder_id());

        assertTrue(isDeleted);
        verify(session, times(1)).delete(order);
    }

    @Test
    public void testGetOrderById() {
        when(session.get(OrdersPlaced.class, order.getOrder_id())).thenReturn(order);

        OrdersPlacedDTO foundOrder = ordersPlacedService.getOrderById(order.getOrder_id());

        assertNotNull(foundOrder);
        verify(session, times(1)).get(OrdersPlaced.class, order.getOrder_id());
    }
    @Test
    public void testOrderNotFoundException() {
        when(session.get(OrdersPlaced.class, order.getOrder_id())).thenReturn(null);

        assertThrows(OrderNotFoundException.class, () -> ordersPlacedService.getOrderById(order.getOrder_id()));
    }
}
