package org.project.InventoryManagementSystemImpl.service;

import org.project.InventoryManagementSystemImpl.entity.Customer;
import java.util.List;
import java.util.UUID;

public interface CustomerService {

    Customer saveCustomer( Customer customer);

    List<Customer> fetchCustomerList();

    Customer updateCustomer(UUID customer_id, Customer customer);

    void deleteCustomerById(UUID customer_id);

    Customer findCustomerById(UUID customer_id);
}
