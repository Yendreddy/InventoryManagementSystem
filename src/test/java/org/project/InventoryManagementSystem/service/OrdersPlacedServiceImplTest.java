package org.project.InventoryManagementSystem.service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project.InventoryManagementSystem.entity.OrdersPlaced;
import org.project.InventoryManagementSystem.exception.OrderNotFoundException;
import org.project.InventoryManagementSystem.repository.OrdersPlacedRepository;

@ExtendWith({MockitoExtension.class})
public class OrdersPlacedServiceImplTest {
    @Mock
    private OrdersPlacedRepository ordersPlacedRepository;
    @InjectMocks
    private OrdersPlacedServiceImpl ordersPlacedService;
    private OrdersPlaced order;
    private UUID orderId;

    public OrdersPlacedServiceImplTest() {
    }

    @BeforeEach
    public void setUp() {
        this.orderId = UUID.randomUUID();
        this.order = new OrdersPlaced();
        this.order.setOrder_id(this.orderId);
        this.order.setQuantity(10);
        this.order.setTotal_price(1000);
    }

    @Test
    public void testGetAllOrders() {
        List<OrdersPlaced> orders = Arrays.asList(this.order);
        Mockito.when(this.ordersPlacedRepository.findAll()).thenReturn(orders);
        List<OrdersPlaced> fetchedOrders = this.ordersPlacedService.getAllOrders();
        Assertions.assertEquals(1, fetchedOrders.size());
        ((OrdersPlacedRepository)Mockito.verify(this.ordersPlacedRepository, Mockito.times(1))).findAll();
    }

    @Test
    public void testGetOrderById() {
        Mockito.when(this.ordersPlacedRepository.findById(this.orderId)).thenReturn(Optional.of(this.order));
        OrdersPlaced fetchedOrder = this.ordersPlacedService.getOrderById(this.orderId);
        Assertions.assertNotNull(fetchedOrder);
        Assertions.assertEquals(10, fetchedOrder.getQuantity());
        ((OrdersPlacedRepository)Mockito.verify(this.ordersPlacedRepository, Mockito.times(1))).findById(this.orderId);
    }

    @Test
    public void testGetOrderById_NotFound() {
        Mockito.when(this.ordersPlacedRepository.findById(this.orderId)).thenReturn(Optional.empty());
        Assertions.assertThrows(OrderNotFoundException.class, () -> this.ordersPlacedService.getOrderById(this.orderId));
        ((OrdersPlacedRepository)Mockito.verify(this.ordersPlacedRepository, Mockito.times(1))).findById(this.orderId);
    }

    @Test
    public void testSaveOrder() {
        Mockito.when((OrdersPlaced)this.ordersPlacedRepository.save(this.order)).thenReturn(this.order);
        OrdersPlaced savedOrder = this.ordersPlacedService.saveOrder(this.order);
        Assertions.assertNotNull(savedOrder);
        Assertions.assertEquals(10, savedOrder.getQuantity());
        ((OrdersPlacedRepository)Mockito.verify(this.ordersPlacedRepository, Mockito.times(1))).save(this.order);
    }

    @Test
    public void testUpdateOrderById() {
        Mockito.when(this.ordersPlacedRepository.findById(this.orderId)).thenReturn(Optional.of(this.order));
        Mockito.when((OrdersPlaced)this.ordersPlacedRepository.save(this.order)).thenReturn(this.order);
        this.order.setQuantity(20);
        OrdersPlaced updatedOrder = this.ordersPlacedService.updateOrderById(this.orderId, this.order);
        Assertions.assertNotNull(updatedOrder);
        Assertions.assertEquals(20, updatedOrder.getQuantity());
        ((OrdersPlacedRepository)Mockito.verify(this.ordersPlacedRepository, Mockito.times(1))).findById(this.orderId);
        ((OrdersPlacedRepository)Mockito.verify(this.ordersPlacedRepository, Mockito.times(1))).save(this.order);
    }

    @Test
    public void testUpdateOrderById_NotFound() {
        Mockito.when(this.ordersPlacedRepository.findById(this.orderId)).thenReturn(Optional.empty());
        Assertions.assertThrows(OrderNotFoundException.class, () -> this.ordersPlacedService.updateOrderById(this.orderId, this.order));
        ((OrdersPlacedRepository)Mockito.verify(this.ordersPlacedRepository, Mockito.times(1))).findById(this.orderId);
    }

    @Test
    public void testDeleteOrderById() {
        Mockito.when(this.ordersPlacedRepository.findById(this.orderId)).thenReturn(Optional.of(this.order));
        ((OrdersPlacedRepository)Mockito.doNothing().when(this.ordersPlacedRepository)).delete(this.order);
        this.ordersPlacedService.deleteOrderById(this.orderId);
        ((OrdersPlacedRepository)Mockito.verify(this.ordersPlacedRepository, Mockito.times(1))).findById(this.orderId);
        ((OrdersPlacedRepository)Mockito.verify(this.ordersPlacedRepository, Mockito.times(1))).delete(this.order);
    }

    @Test
    public void testDeleteOrderById_NotFound() {
        Mockito.when(this.ordersPlacedRepository.findById(this.orderId)).thenReturn(Optional.empty());
        Assertions.assertThrows(OrderNotFoundException.class, () -> this.ordersPlacedService.deleteOrderById(this.orderId));
        ((OrdersPlacedRepository)Mockito.verify(this.ordersPlacedRepository, Mockito.times(1))).findById(this.orderId);
    }
}
