package org.project.InventoryManagementSystem.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project.InventoryManagementSystem.entity.Customer;
import org.project.InventoryManagementSystem.exception.CustomerNotFoundException;
import org.project.InventoryManagementSystem.repository.CustomerRepository;

@ExtendWith({MockitoExtension.class})
public class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerServiceImpl customerService;
    private Customer customer;
    private UUID customerId;

    public CustomerServiceImplTest() {
    }

    @BeforeEach
    public void setUp() {
        this.customerId = UUID.randomUUID();
        this.customer = new Customer();
        this.customer.setCustomer_id(this.customerId);
        this.customer.setName("John Doe");
        this.customer.setPhone_number("1234567890");
        this.customer.setEmail_id("john.doe@example.com");
    }

    @Test
    public void testFetchCustomerList() {
        List<Customer> customers = Arrays.asList(this.customer);
        Mockito.when(this.customerRepository.findAll()).thenReturn(customers);
        List<Customer> fetchedCustomers = this.customerService.fetchCustomerList();
        Assertions.assertEquals(1, fetchedCustomers.size());
        ((CustomerRepository)Mockito.verify(this.customerRepository, Mockito.times(1))).findAll();
    }

    @Test
    public void testGetCustomerById() {
        Mockito.when(this.customerRepository.findById(this.customerId)).thenReturn(Optional.of(this.customer));
        Customer fetchedCustomer = this.customerService.getCustomerById(this.customerId);
        Assertions.assertNotNull(fetchedCustomer);
        Assertions.assertEquals("John Doe", fetchedCustomer.getName());
        ((CustomerRepository)Mockito.verify(this.customerRepository, Mockito.times(1))).findById(this.customerId);
    }

    @Test
    public void testGetCustomerById_NotFound() {
        Mockito.when(this.customerRepository.findById(this.customerId)).thenReturn(Optional.empty());
        Assertions.assertThrows(CustomerNotFoundException.class, () -> this.customerService.getCustomerById(this.customerId));
        ((CustomerRepository)Mockito.verify(this.customerRepository, Mockito.times(1))).findById(this.customerId);
    }

    @Test
    public void testSaveCustomer() {
        Mockito.when((Customer)this.customerRepository.save(this.customer)).thenReturn(this.customer);
        Customer savedCustomer = this.customerService.saveCustomer(this.customer);
        Assertions.assertNotNull(savedCustomer);
        Assertions.assertEquals("John Doe", savedCustomer.getName());
        ((CustomerRepository)Mockito.verify(this.customerRepository, Mockito.times(1))).save(this.customer);
    }

    @Test
    public void testUpdateCustomer() {
        Mockito.when(this.customerRepository.findById(this.customerId)).thenReturn(Optional.of(this.customer));
        Mockito.when((Customer)this.customerRepository.save(this.customer)).thenReturn(this.customer);
        this.customer.setName("Updated John Doe");
        Customer updatedCustomer = this.customerService.updateCustomer(this.customerId, this.customer);
        Assertions.assertNotNull(updatedCustomer);
        Assertions.assertEquals("Updated John Doe", updatedCustomer.getName());
        ((CustomerRepository)Mockito.verify(this.customerRepository, Mockito.times(1))).findById(this.customerId);
        ((CustomerRepository)Mockito.verify(this.customerRepository, Mockito.times(1))).save(this.customer);
    }

    @Test
    public void testUpdateCustomer_NotFound() {
        Mockito.when(this.customerRepository.findById(this.customerId)).thenReturn(Optional.empty());
        Assertions.assertThrows(CustomerNotFoundException.class, () -> this.customerService.updateCustomer(this.customerId, this.customer));
        ((CustomerRepository)Mockito.verify(this.customerRepository, Mockito.times(1))).findById(this.customerId);
    }

    @Test
    public void testDeleteCustomerById() {
        Mockito.when(this.customerRepository.findById(this.customerId)).thenReturn(Optional.of(this.customer));
        ((CustomerRepository)Mockito.doNothing().when(this.customerRepository)).delete(this.customer);
        this.customerService.deleteCustomerById(this.customerId);
        ((CustomerRepository)Mockito.verify(this.customerRepository, Mockito.times(1))).findById(this.customerId);
        ((CustomerRepository)Mockito.verify(this.customerRepository, Mockito.times(1))).delete(this.customer);
    }

    @Test
    public void testDeleteCustomerById_NotFound() {
        Mockito.when(this.customerRepository.findById(this.customerId)).thenReturn(Optional.empty());
        Assertions.assertThrows(CustomerNotFoundException.class, () -> this.customerService.deleteCustomerById(this.customerId));
        ((CustomerRepository)Mockito.verify(this.customerRepository, Mockito.times(1))).findById(this.customerId);
    }
}
