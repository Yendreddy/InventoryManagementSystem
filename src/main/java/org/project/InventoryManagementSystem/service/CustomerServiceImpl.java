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
public class CustomerServiceImpl implements CustomerService{

    @Override
    public Customer saveCustomer(Customer customer) {
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
            session.save(customer);
            session.getTransaction().commit();
            return customer;
        }
    }

    @Override
    public  List<Customer> fetchCustomerList() {
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
            List<Customer> customers = session.createQuery("FROM Customer", Customer.class).list();
            session.getTransaction().commit();
            return customers;
        }
    }

    @Override
    public Customer updateCustomer(UUID customerId, Customer customer) {
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
            session.merge(customer);
            session.getTransaction().commit();
            return customer;
        }
    }

    @Override
    public void deleteCustomerById(UUID customerId) {
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
            Customer customer = session.get(Customer.class, customerId);
            if (customer != null) {
                session.delete(customer);
            }
            session.getTransaction().commit();
        }
    }
}
//class test{
//    public static void main(String[] args) {
//        Customer c = new Customer();
//        c.setName("asas");
//        c.setEmailId("a@gmail.com");
//        c.setPhoneNumber("1212");
//        System.out.println(c);
//
//        Customer s = new CustomerServiceImpl().saveCustomer(c);
//        System.out.println(s);
//
//
//    }
