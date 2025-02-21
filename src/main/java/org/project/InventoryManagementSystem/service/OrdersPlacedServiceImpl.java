package org.project.InventoryManagementSystem.service;

import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.project.InventoryManagementSystem.entity.*;
import org.project.InventoryManagementSystem.exception.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrdersPlacedServiceImpl implements OrdersPlacedService {

    @Autowired
    private SessionFactory sessionFactory;

    public OrdersPlacedServiceImpl() {
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(Customer.class);
        cfg.addAnnotatedClass(OrdersPlaced.class);
        cfg.addAnnotatedClass(Product.class);
        cfg.addAnnotatedClass(Category.class);
        cfg.configure();
        this.sessionFactory = cfg.buildSessionFactory();
    }

    @Transactional
    @Override
    public List<OrdersPlaced> getAllOrders() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<OrdersPlaced> orders = session.createQuery("FROM OrdersPlaced", OrdersPlaced.class).list();
            session.getTransaction().commit();
            return orders;
        }
    }

    @Transactional
    @Override
    public OrdersPlaced saveOrder(OrdersPlaced order) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(order);
            session.flush();
            order.calculateTotalPrice(session);
            Product product = session.get(Product.class, order.getProduct_id());
            if (product != null) {
                product.setQuantity(product.getQuantity() - order.getQuantity());
                session.update(product);
            }
            session.update(order);
            session.getTransaction().commit();
            return session.get(OrdersPlaced.class, order.getOrder_id());
        }
    }

    @Transactional
    @Override
    public OrdersPlaced updateOrderById(UUID order_id, OrdersPlaced orders) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            OrdersPlaced existingOrder = session.get(OrdersPlaced.class, order_id);
            if (existingOrder != null) {
                existingOrder.setOrderItems(orders.getOrderItems());
                existingOrder.setTotal_price(orders.getTotal_price());
                session.merge(existingOrder);
                session.getTransaction().commit();
                return existingOrder;
            } else {
                throw new OrderNotFoundException("Order not found" + order_id);
            }
        }
    }

    @Transactional
    @Override
    public void deleteOrderById(UUID order_id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            OrdersPlaced ordersPlaced = session.get(OrdersPlaced.class, order_id);
            if (ordersPlaced != null) {
                session.delete(ordersPlaced);
                session.getTransaction().commit();
            } else {
                throw new OrderNotFoundException("Order not found" + order_id);
            }
        }
    }

    @Transactional
    @Override
    public OrdersPlaced getOrderById(UUID order_id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            OrdersPlaced order = session.get(OrdersPlaced.class, order_id);
            session.getTransaction().commit();
            if (order != null) {
                return order;
            } else {
                throw new OrderNotFoundException("Order not found"+ order_id);
            }
        }
    }
}