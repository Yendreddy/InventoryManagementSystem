package org.project.InventoryManagementSystem.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.project.InventoryManagementSystem.entity.Category;
import org.project.InventoryManagementSystem.entity.Customer;
import org.project.InventoryManagementSystem.entity.OrdersPlaced;
import org.project.InventoryManagementSystem.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrdersPlacedServiceImpl implements OrdersPlacedService {
    @Override
    public List<OrdersPlaced> getAllOrders() {
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(Customer.class);
        cfg.addAnnotatedClass(OrdersPlaced.class);
        cfg.addAnnotatedClass(Product.class);
        cfg.addAnnotatedClass(Category.class);
        cfg.configure();
        SessionFactory sf = cfg.buildSessionFactory();
        try (
                Session session = sf.openSession()) {
            session.beginTransaction();
            List<OrdersPlaced> orders = session.createQuery("FROM OrderPlaced", OrdersPlaced.class).list();
            session.getTransaction().commit();
            return orders;
        }
    }

    @Override
    public OrdersPlaced saveOrder(OrdersPlaced order) {
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(Customer.class);
        cfg.addAnnotatedClass(OrdersPlaced.class);
        cfg.addAnnotatedClass(Product.class);
        cfg.addAnnotatedClass(Category.class);
        cfg.configure();
        SessionFactory sf = cfg.buildSessionFactory();
        try (
                Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(order);
            session.getTransaction().commit();
            return session.get(OrdersPlaced.class, order);
        }
    }

    @Override
    public OrdersPlaced updateOrderById(UUID orderId, OrdersPlaced orders) {
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(Customer.class);
        cfg.addAnnotatedClass(OrdersPlaced.class);
        cfg.addAnnotatedClass(Product.class);
        cfg.addAnnotatedClass(Category.class);
        cfg.configure();
        SessionFactory sf = cfg.buildSessionFactory();
        try (
                Session session = sf.openSession()) {
            session.beginTransaction();
            session.merge(orders);
            session.getTransaction().commit();
            return orders;
        }
    }

    @Override
    public void deleteOrderById(UUID orderId) {

        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(Customer.class);
        cfg.addAnnotatedClass(OrdersPlaced.class);
        cfg.addAnnotatedClass(Product.class);
        cfg.addAnnotatedClass(Category.class);
        cfg.configure();
        SessionFactory sf = cfg.buildSessionFactory();
        try (
                Session session = sf.openSession()) {
            session.beginTransaction();
            OrdersPlaced ordersPlaced = session.get(OrdersPlaced.class, orderId);
            if (ordersPlaced != null) {
                session.delete(ordersPlaced);
            }
            session.getTransaction().commit();
        }
    }
}
