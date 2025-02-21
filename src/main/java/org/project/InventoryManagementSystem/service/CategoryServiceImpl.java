package org.project.InventoryManagementSystem.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.project.InventoryManagementSystem.entity.Category;
import org.project.InventoryManagementSystem.entity.Customer;
import org.project.InventoryManagementSystem.entity.OrdersPlaced;
import org.project.InventoryManagementSystem.entity.Product;
import org.project.InventoryManagementSystem.exception.CategoryNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private SessionFactory sessionFactory;

    public CategoryServiceImpl() {
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(Customer.class);
        cfg.addAnnotatedClass(OrdersPlaced.class);
        cfg.addAnnotatedClass(Product.class);
        cfg.addAnnotatedClass(Category.class);
        cfg.configure();
        this.sessionFactory = cfg.buildSessionFactory();
    }

    @Override
    public Category saveCategory(Category category) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(category);
            session.getTransaction().commit();
            return category;
        }
    }

    @Override
    public List<Category> fetchCategoryList() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Category> categories = session.createQuery("FROM Category", Category.class).list();
            session.getTransaction().commit();
            return categories;
        }
    }

    @Override
    public Category updateCategoryById(UUID category_id, Category category) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Category existingCategory = session.get(Category.class, category_id);
            if (existingCategory != null) {
                existingCategory.setName(category.getName());
                session.merge(existingCategory);
                session.getTransaction().commit();
                return existingCategory;
            } else {
                throw new CategoryNotFoundException("Category not found" +category_id);
            }
        }
    }

    @Override
    public void deleteCategoryById(UUID category_id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Category category = session.get(Category.class, category_id);
            if (category != null) {
                session.delete(category);
                session.getTransaction().commit();
            } else {
                throw new CategoryNotFoundException("Category not found" +category_id);
            }
        }
    }
    @Override
    public Category findCategoryById(UUID category_id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Category category = session.get(Category.class, category_id);
            session.getTransaction().commit();
            if (category != null) {
                return category;
            } else {
                throw new CategoryNotFoundException("Category not found" +category_id);
            }
        }
    }
}