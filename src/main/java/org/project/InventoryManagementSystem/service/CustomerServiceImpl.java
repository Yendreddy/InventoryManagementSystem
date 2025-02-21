package org.project.InventoryManagementSystem.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.project.InventoryManagementSystem.entity.Category;
import org.project.InventoryManagementSystem.entity.Customer;
import org.project.InventoryManagementSystem.entity.OrdersPlaced;
import org.project.InventoryManagementSystem.entity.Product;
import org.project.InventoryManagementSystem.exception.CustomerNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private SessionFactory sessionFactory;

    public CustomerServiceImpl() {
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(Customer.class);
        cfg.addAnnotatedClass(OrdersPlaced.class);
        cfg.addAnnotatedClass(Product.class);
        cfg.addAnnotatedClass(Category.class);
        cfg.configure();
        this.sessionFactory = cfg.buildSessionFactory();
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(customer);
            session.getTransaction().commit();
            return customer;
        }
    }

    @Override
    public List<Customer> fetchCustomerList() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Customer> customers = session.createQuery("FROM Customer", Customer.class).list();
            session.getTransaction().commit();
            return customers;
        }
    }
    @Override
    public Customer updateCustomer(UUID customer_id, Customer customer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Customer existingCustomer = session.get(Customer.class, customer_id);
            if (existingCustomer != null) {
                existingCustomer.setName(customer.getName());
                existingCustomer.setPhone_number(customer.getPhone_number());
                existingCustomer.setEmail_id(customer.getEmail_id());
                session.merge(existingCustomer);
                session.getTransaction().commit();
                return existingCustomer;
            } else {
                throw new CustomerNotFoundException("Customer not found" + customer_id);
            }
        }
    }

    @Override
    public void deleteCustomerById(UUID customer_id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, customer_id);
            if (customer != null) {
                session.delete(customer);
                session.getTransaction().commit();
            } else {
                throw new CustomerNotFoundException("Customer not found" +customer_id);
            }
        }
    }

    @Transactional
    @Override
    public Customer findCustomerById(UUID customer_id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, customer_id);
            session.getTransaction().commit();
            if (customer != null) {
                return customer;
            } else {
                throw new CustomerNotFoundException("Customer not found" + customer_id);
            }
        }
    }
}
