package org.project.InventoryManagementSystem.service;

import org.project.InventoryManagementSystem.dto.CustomerDTO;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    boolean saveCustomer(CustomerDTO customerDTO);
    List<CustomerDTO> fetchCustomerList();
    CustomerDTO getCustomerById(UUID customer_id);
    boolean updateCustomer(UUID customer_id, CustomerDTO customerDTO);
    boolean deleteCustomerById(UUID customer_id);
}
