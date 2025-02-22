package org.project.InventoryManagementSystem.service;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.modelmapper.ModelMapper;
import org.project.InventoryManagementSystem.dto.CustomerDTO;
import org.project.InventoryManagementSystem.entity.Customer;
import org.project.InventoryManagementSystem.exception.CustomerNotFoundException;
import org.project.InventoryManagementSystem.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final SessionFactory sessionFactory;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(ModelMapper modelMapper) {
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(Customer.class);
        cfg.configure();
        this.sessionFactory = cfg.buildSessionFactory();
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CustomerDTO> fetchCustomerList() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Customer> customers = session.createQuery("FROM Customer", Customer.class).list();
            session.getTransaction().commit();
            return customers.stream()
                    .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public CustomerDTO getCustomerById(UUID customer_id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, customer_id);
            session.getTransaction().commit();
            if (customer != null) {
                return modelMapper.map(customer, CustomerDTO.class);
            } else {
                throw new CustomerNotFoundException("Customer", "id", customer_id);
            }
        }
    }

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Customer customer = modelMapper.map(customerDTO, Customer.class);
            session.save(customer);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateCustomer(UUID customer_id, CustomerDTO customerDTO) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Customer existingCustomer = session.get(Customer.class, customer_id);
            if (existingCustomer != null) {
                existingCustomer.setName(customerDTO.getName());
                existingCustomer.setPhone_number(customerDTO.getPhone_number());
                existingCustomer.setEmail_id(customerDTO.getEmail_id());
                session.merge(existingCustomer);
                session.getTransaction().commit();
                return true;
            } else {
                throw new CustomerNotFoundException("Customer", "id", customer_id);
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteCustomerById(UUID customer_id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, customer_id);
            if (customer != null) {
                session.delete(customer);
                session.getTransaction().commit();
                return true;
            } else {
                throw new CustomerNotFoundException("Customer", "id", customer_id);
            }
        } catch (Exception e) {
            return false;
        }
    }
}