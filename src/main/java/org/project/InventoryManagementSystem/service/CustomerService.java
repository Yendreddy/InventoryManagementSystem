package org.project.InventoryManagementSystem.service;

import org.project.InventoryManagementSystem.entity.Customer;
import org.project.InventoryManagementSystem.exception.CategoryNotFoundException;
import org.project.InventoryManagementSystem.exception.CustomerNotFoundException;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    /**
     * Saves a new customer.
     *
     * @param customer The {@link Customer} object representing the customer to save.
     * @return customer.
     */
    Customer saveCustomer(Customer customer);

    /**
     * Fetches the list of all customers.
     *
     * @return A list of {@link Customer} objects representing all customers.
     */
    List<Customer> fetchCustomerList();

    /**
     * Finds a customer by its ID.
     *
     * @param customer_id The UUID of the category to find.
     * @return A {@link Customer} object representing the found category.
     * @throws CustomerNotFoundException if the category is not found.
     */
    Customer getCustomerById(UUID customer_id);

    /**
     * Updates an existing customer by its ID.
     *
     * @param customer_id The UUID of the customer to update.
     * The {@link Customer} object representing the updated customer.
     * @return updated customer.
     * @throws CustomerNotFoundException if the customer is not found.
     */
    Customer updateCustomer(UUID customer_id, Customer customer);

    /**
     * Deletes a customer by its ID.
     *
     * @param customer_id The UUID of the customer to delete.
     * @throws CustomerNotFoundException if the customer is not found.
     */
    void deleteCustomerById(UUID customer_id);
}
