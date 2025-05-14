package org.project.InventoryManagementSystemImpl.service;

import org.project.InventoryManagementSystemImpl.entity.Customer;
import org.project.InventoryManagementSystemImpl.exception.CustomerNotFoundException;
import org.project.InventoryManagementSystemImpl.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> fetchCustomerList() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(UUID customer_id, Customer customer) {
        return customerRepository.findById(customer_id)
                .map(existingCustomer -> {
                    existingCustomer.setName(customer.getName());
                    existingCustomer.setPhone_number(customer.getPhone_number());
                    existingCustomer.setEmail_id(customer.getEmail_id());
                    return customerRepository.save(existingCustomer);
                })
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found" + customer_id));
    }

    @Override
    public void deleteCustomerById(UUID customer_id) {
        Customer customer = customerRepository.findById(customer_id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found" + customer_id));
        customerRepository.delete(customer);
    }

    @Override
    public Customer findCustomerById(UUID customer_id) {
        return customerRepository.findById(customer_id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found" + customer_id));
    }
}
