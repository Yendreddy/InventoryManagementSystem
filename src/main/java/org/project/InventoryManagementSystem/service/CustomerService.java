package org.project.InventoryManagementSystem.service;

import jakarta.validation.Valid;
import org.project.InventoryManagementSystem.entity.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    Customer saveCustomer( Customer customer);

    List<Customer> fetchCustomerList();

    Customer updateCustomer(UUID customerId, Customer customer);

    void deleteCustomerById(UUID customerId);
}
