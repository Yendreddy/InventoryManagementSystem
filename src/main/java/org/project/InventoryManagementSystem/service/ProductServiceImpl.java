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
public class ProductServiceImpl implements ProductService{
    @Override
    public Product saveProduct(Product product) {
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
            session.save(product);
            session.getTransaction().commit();
            return session.get(Product.class, product);
        }
    }

    @Override
    public List<Product> fetchProductList() {
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
            List<Product> products = session.createQuery("FROM Product", Product.class).list();
            session.getTransaction().commit();
            return products;
        }
    }

    @Override
    public Product updateProduct(UUID productId, Product product) {
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
            session.merge(product);
            session.getTransaction().commit();
            return product;
        }
    }

    @Override
    public void deleteCustomerById(UUID productId) {
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
            Product product = session.get(Product.class, productId);
            if (product != null) {
                session.delete(product);
            }
            session.getTransaction().commit();
        }
    }
}
