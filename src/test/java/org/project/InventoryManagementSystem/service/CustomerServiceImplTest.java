package org.project.InventoryManagementSystem.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project.InventoryManagementSystem.entity.Customer;
import org.project.InventoryManagementSystem.exception.CustomerNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;


    private Transaction transaction;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = Customer.builder()
                .customer_id(UUID.randomUUID())
                .name("John Doe")
                .phone_number("1234567890")
                .email_id("john.doe@example.com")
                .build();

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
    }

    @Test
    public void testGetAllCustomers() {
        when(session.createQuery("FROM Customer", Customer.class).list()).thenReturn(Collections.singletonList(customer));

        List<Customer> customers = customerService.fetchCustomerList();

        assertNotNull(customers);
        assertEquals(1, customers.size());
        verify(session, times(1)).createQuery("FROM Customer", Customer.class);
    }

    @Test
    public void testSaveCustomer() {
        Customer savedCustomer = customerService.saveCustomer(customer);

        assertNotNull(savedCustomer);
        verify(session, times(1)).save(customer);
    }

    @Test
    public void testUpdateCustomerById() {
        when(session.get(Customer.class, customer.getCustomer_id())).thenReturn(customer);

        Customer updatedCustomer = customerService.updateCustomer(customer.getCustomer_id(), customer);

        assertNotNull(updatedCustomer);
        verify(session, times(1)).merge(customer);
    }

    @Test
    public void testDeleteCustomerById() {
        when(session.get(Customer.class, customer.getCustomer_id())).thenReturn(customer);

        customerService.deleteCustomerById(customer.getCustomer_id());

        verify(session, times(1)).delete(customer);
    }

    @Test
    public void testGetCustomerById() {
        when(session.get(Customer.class, customer.getCustomer_id())).thenReturn(customer);

        Customer foundCustomer = customerService.findCustomerById(customer.getCustomer_id());

        assertNotNull(foundCustomer);
        verify(session, times(1)).get(Customer.class, customer.getCustomer_id());
    }

    @Test
    public void testCustomerNotFoundException() {
        when(session.get(Customer.class, customer.getCustomer_id())).thenReturn(null);

        assertThrows(CustomerNotFoundException.class, () -> customerService.findCustomerById(customer.getCustomer_id()));
    }
}