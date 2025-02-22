package org.project.InventoryManagementSystem.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.modelmapper.ModelMapper;
import org.project.InventoryManagementSystem.dto.CategoryDTO;
import org.project.InventoryManagementSystem.dto.CustomerDTO;
import org.project.InventoryManagementSystem.dto.OrdersPlacedDTO;
import org.project.InventoryManagementSystem.dto.ProductDTO;
import org.project.InventoryManagementSystem.entity.Category;
import org.project.InventoryManagementSystem.entity.Customer;
import org.project.InventoryManagementSystem.entity.OrdersPlaced;
import org.project.InventoryManagementSystem.entity.Product;
import org.project.InventoryManagementSystem.exception.OrderNotFoundException;
import org.project.InventoryManagementSystem.service.OrdersPlacedService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrdersPlacedServiceImpl implements OrdersPlacedService {

    private final SessionFactory sessionFactory;
    private final ModelMapper modelMapper;

    public OrdersPlacedServiceImpl(ModelMapper modelMapper) {
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(OrdersPlaced.class);
        cfg.addAnnotatedClass(CustomerDTO.class);
        cfg.addAnnotatedClass(ProductDTO.class);
        cfg.addAnnotatedClass(CategoryDTO.class);
        cfg.configure();
        this.sessionFactory = cfg.buildSessionFactory();
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OrdersPlacedDTO> getAllOrders() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<OrdersPlaced> orders = session.createQuery("FROM OrdersPlaced", OrdersPlaced.class).list();
            session.getTransaction().commit();
            return orders.stream()
                    .map(order -> modelMapper.map(order, OrdersPlacedDTO.class))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public OrdersPlacedDTO getOrderById(UUID order_id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            OrdersPlaced order = session.get(OrdersPlaced.class, order_id);
            session.getTransaction().commit();
            if (order != null) {
                return modelMapper.map(order, OrdersPlacedDTO.class);
            } else {
                throw new OrderNotFoundException("Order", "id", order_id);
            }
        }
    }

    @Override
    public boolean saveOrder(OrdersPlacedDTO ordersPlacedDTO) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            OrdersPlaced order = modelMapper.map(ordersPlacedDTO, OrdersPlaced.class);
            order.setCustomer(session.get(Customer.class, ordersPlacedDTO.getCustomer_id()));
            order.setProduct_id(session.get(Product.class, ordersPlacedDTO.getProduct_id()).getProduct_id());
            order.setCategory_id(session.get(Category.class, ordersPlacedDTO.getCategory_id()).getCategory_id());
            session.save(order);
            session.flush();
            order.calculateTotalPrice(session);
            Product product = session.get(Product.class, ordersPlacedDTO.getProduct_id());
            if(product != null){
                product.setQuantity(product.getQuantity() - order.getQuantity());
                session.update(product);
            }
            session.update(order);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateOrderById(UUID order_id, OrdersPlacedDTO ordersPlacedDTO) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            OrdersPlaced existingOrder = session.get(OrdersPlaced.class, order_id);
            if (existingOrder != null) {
                existingOrder.setQuantity(ordersPlacedDTO.getQuantity());
                existingOrder.setTotal_price(ordersPlacedDTO.getTotal_price());
                existingOrder.setCustomer(modelMapper.map(ordersPlacedDTO.getCustomer_id(), Customer.class));
                existingOrder.setProduct_id(modelMapper.map(ordersPlacedDTO.getProduct_id(), Product.class).getProduct_id());
                existingOrder.setCategory_id(modelMapper.map(ordersPlacedDTO.getCategory_id(), Category.class).getCategory_id());
                session.merge(existingOrder);
                session.getTransaction().commit();
                return true;
            } else {
                throw new OrderNotFoundException("Order", "id", order_id);
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteOrderById(UUID order_id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            OrdersPlaced order = session.get(OrdersPlaced.class, order_id);
            if (order != null) {
                session.delete(order);
                session.getTransaction().commit();
                return true;
            } else {
                throw new OrderNotFoundException("Order", "id", order_id);
            }
        } catch (Exception e) {
            return false;
        }
    }
}