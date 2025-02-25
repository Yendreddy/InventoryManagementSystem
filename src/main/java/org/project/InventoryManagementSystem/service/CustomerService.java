package org.project.InventoryManagementSystem.service;

import org.project.InventoryManagementSystem.dto.CategoryDTO;
import org.project.InventoryManagementSystem.dto.CustomerDTO;
import org.project.InventoryManagementSystem.exception.CategoryNotFoundException;
import org.project.InventoryManagementSystem.exception.CustomerNotFoundException;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    /**
     * Saves a new customer.
     *
     * @param customerDTO The {@link CustomerDTO} object representing the customer to save.
     * @return true if the customer is saved successfully, false otherwise.
     */
    boolean saveCustomer(CustomerDTO customerDTO);

    /**
     * Fetches the list of all customers.
     *
     * @return A list of {@link CustomerDTO} objects representing all customers.
     */
    List<CustomerDTO> fetchCustomerList();

    /**
     * Finds a customer by its ID.
     *
     * @param customer_id The UUID of the category to find.
     * @return A {@link CustomerDTO} object representing the found category.
     * @throws CustomerNotFoundException if the category is not found.
     */
    CustomerDTO getCustomerById(UUID customer_id);

    /**
     * Updates an existing customer by its ID.
     *
     * @param customer_id The UUID of the customer to update.
     * The {@link CustomerDTO} object representing the updated customer.
     * @return true if the customer is updated successfully, false otherwise.
     * @throws CustomerNotFoundException if the customer is not found.
     */
    boolean updateCustomer(UUID customer_id, CustomerDTO customerDTO);

    /**
     * Deletes a customer by its ID.
     *
     * @param customer_id The UUID of the customer to delete.
     * @return true if the customer is deleted successfully, false otherwise.
     * @throws CustomerNotFoundException if the customer is not found.
     */
    boolean deleteCustomerById(UUID customer_id);
}
