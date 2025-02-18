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
public class CategoryServiceImpl implements CategoryService{
    @Override
    public Category saveCategory(Category category) {
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
            session.save(category);
            session.getTransaction().commit();
            return category;
        }
    }

    @Override
    public List<Category> fetchCategoryList() {
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
            List<Category> categories = session.createQuery("FROM Category", Category.class).list();
            session.getTransaction().commit();
            return categories;
        }
    }

    @Override
    public Category updateCategoryById(UUID categoryId, Category category) {
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
            session.merge(category);
            session.getTransaction().commit();
            return category;
        }
    }

    @Override
    public void deleteCategoryById(UUID categoryId) {
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
            Category category = session.get(Category.class, categoryId);
            if (category != null) {
                session.delete(category);
            }
            session.getTransaction().commit();
        }
    }
}
