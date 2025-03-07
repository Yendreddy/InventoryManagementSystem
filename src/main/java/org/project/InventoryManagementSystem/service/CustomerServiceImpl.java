package org.project.InventoryManagementSystem.service;


import java.util.List;
import java.util.UUID;
import org.project.InventoryManagementSystem.entity.Customer;
import org.project.InventoryManagementSystem.exception.CustomerNotFoundException;
import org.project.InventoryManagementSystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerServiceImpl() {
    }

    @Cacheable({"customers"})
    public List<Customer> fetchCustomerList() {
        return this.customerRepository.findAll();
    }

    @Cacheable(
            value = {"customers"},
            key = "#customer_id"
    )
    public Customer getCustomerById(UUID customer_id) {
        return (Customer)this.customerRepository.findById(customer_id).orElseThrow(() -> new CustomerNotFoundException("Customer", "id", customer_id));
    }

    @CacheEvict(
            value = {"customers"},
            allEntries = true
    )
    public Customer saveCustomer(Customer customer) {
        return (Customer)this.customerRepository.save(customer);
    }

    @CacheEvict(
            value = {"customers"},
            key = "#customer_id"
    )
    public Customer updateCustomer(UUID customer_id, Customer customer) {
        Customer existingCustomer = (Customer)this.customerRepository.findById(customer_id).orElseThrow(() -> new CustomerNotFoundException("Customer", "id", customer_id));
        existingCustomer.setName(customer.getName());
        existingCustomer.setPhone_number(customer.getPhone_number());
        existingCustomer.setEmail_id(customer.getEmail_id());
        return (Customer)this.customerRepository.save(existingCustomer);
    }

    @CacheEvict(
            value = {"customers"},
            key = "#customer_id"
    )
    public void deleteCustomerById(UUID customer_id) {
        Customer customer = (Customer)this.customerRepository.findById(customer_id).orElseThrow(() -> new CustomerNotFoundException("Customer", "id", customer_id));
        this.customerRepository.delete(customer);
    }
}
