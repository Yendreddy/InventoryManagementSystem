package org.project.InventoryManagementSystemImpl.service;

import org.modelmapper.ModelMapper;
import org.project.InventoryManagementSystemImpl.dto.OrdersPlacedDTO;
import org.project.InventoryManagementSystemImpl.entity.Category;
import org.project.InventoryManagementSystemImpl.entity.Customer;
import org.project.InventoryManagementSystemImpl.entity.OrdersPlaced;
import org.project.InventoryManagementSystemImpl.entity.Product;
import org.project.InventoryManagementSystemImpl.exception.OrderNotFoundException;
import org.project.InventoryManagementSystemImpl.exception.ProductNotFoundException;
import org.project.InventoryManagementSystemImpl.repository.CategoryRepository;
import org.project.InventoryManagementSystemImpl.repository.CustomerRepository;
import org.project.InventoryManagementSystemImpl.repository.OrdersPlacedRepository;
import org.project.InventoryManagementSystemImpl.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrdersPlacedServiceImpl implements OrdersPlacedService {

    @Autowired
    private OrdersPlacedRepository ordersPlacedRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<OrdersPlacedDTO> getAllOrders() {
        return ordersPlacedRepository.findAll().stream()
                .map(order -> modelMapper.map(order, OrdersPlacedDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrdersPlacedDTO getOrderById(UUID order_id) {
        OrdersPlaced order = ordersPlacedRepository.findById(order_id)
                .orElseThrow(() -> new OrderNotFoundException(order_id));
        return modelMapper.map(order, OrdersPlacedDTO.class);
    }

    @Override
    @Transactional
    public boolean saveOrder(OrdersPlacedDTO dto) {
        try {
            OrdersPlaced order = modelMapper.map(dto, OrdersPlaced.class);

            Product product = productRepository.findById(dto.getProduct_id())
                    .orElseThrow(() -> new ProductNotFoundException(dto.getProduct_id()));

            Customer customer = customerRepository.findById(dto.getCustomer_id())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));

            Category category = categoryRepository.findById(dto.getCategory_id())
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            order.setCustomer(customer);
            order.setProduct_id(product.getProduct_id());
            order.setCategory_id(category.getCategory_id());
            order.setTotal_price(product.getPrice() * order.getQuantity());
            order.calculateTotalPrice();
            ordersPlacedRepository.save(order);
            product.setQuantity(product.getQuantity() - order.getQuantity());
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            // Log exception here if needed
            return false;
        }
    }

    @Override
    @Transactional
    public boolean updateOrderById(UUID orderId, OrdersPlacedDTO dto) {
        try {
            OrdersPlaced existingOrder = ordersPlacedRepository.findById(orderId)
                    .orElseThrow(() -> new OrderNotFoundException(orderId));

            Product product = productRepository.findById(dto.getProduct_id())
                    .orElseThrow(() -> new ProductNotFoundException(dto.getProduct_id()));

            Customer customer = customerRepository.findById(dto.getCustomer_id())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));

            Category category = categoryRepository.findById(dto.getCategory_id())
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            existingOrder.setQuantity(dto.getQuantity());
            existingOrder.setCustomer(customer);
            existingOrder.setProduct_id(product.getProduct_id());
            existingOrder.setCategory_id(category.getCategory_id());
            existingOrder.calculateTotalPrice();
            existingOrder.setTotal_price(product.getPrice() * existingOrder.getQuantity());

            ordersPlacedRepository.save(existingOrder);
            return true;
        } catch (Exception e) {
            // Log exception here if needed
            return false;
        }
    }

    @Override
    @Transactional
    public boolean deleteOrderById(UUID orderId) {
        try {
            OrdersPlaced order = ordersPlacedRepository.findById(orderId)
                    .orElseThrow(() -> new OrderNotFoundException(orderId));

            ordersPlacedRepository.delete(order);
            return true;
        } catch (Exception e) {
            // Log exception here if needed
            return false;
        }
    }
}