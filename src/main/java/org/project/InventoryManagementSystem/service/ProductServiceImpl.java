package org.project.InventoryManagementSystem.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.project.InventoryManagementSystem.entity.Category;
import org.project.InventoryManagementSystem.entity.Customer;
import org.project.InventoryManagementSystem.entity.OrdersPlaced;
import org.project.InventoryManagementSystem.entity.Product;
import org.project.InventoryManagementSystem.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{
    private SessionFactory sessionFactory;

    public ProductServiceImpl() {
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(Customer.class);
        cfg.addAnnotatedClass(OrdersPlaced.class);
        cfg.addAnnotatedClass(Product.class);
        cfg.addAnnotatedClass(Category.class);
        cfg.configure();
        this.sessionFactory = cfg.buildSessionFactory();
    }

    @Override
    public Product saveProduct(Product product) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
            return product;
        }
    }

    @Override
    public List<Product> fetchProductList() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Product> products = session.createQuery("FROM Product", Product.class).list();
            session.getTransaction().commit();
            return products;
        }
    }

    @Override
    public Product updateProduct(UUID product_id, Product product) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Product existingProduct = session.get(Product.class, product_id);
            if (existingProduct != null) {
                existingProduct.setName(product.getName());
                existingProduct.setPrice(product.getPrice());
                existingProduct.setQuantity(product.getQuantity());
                session.merge(existingProduct);
                session.getTransaction().commit();
                return existingProduct;
            } else {
                throw new ProductNotFoundException("Product not found"+product_id);
            }
        }
    }

    @Override
    public void deleteProductById(UUID product_id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, product_id);
            if (product != null) {
                session.delete(product);
                session.getTransaction().commit();
            } else {
                throw new ProductNotFoundException("Product not found" + product_id);
            }
        }
    }

    @Override
    public Product findProductById(UUID product_id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, product_id);
            session.getTransaction().commit();
            if (product != null) {
                return product;
            } else {
                throw new ProductNotFoundException("Product not found" + product_id);
            }
        }
    }
}
