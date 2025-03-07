package org.project.InventoryManagementSystemImpl.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.project.InventoryManagementSystemImpl.entity.Customer;
import org.project.InventoryManagementSystemImpl.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
@Tag(name = "Customer", description = "Customer management APIs")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/get")
    @Operation(summary = "Get all customers")
    public List<Customer> getAllCustomers() {
        return customerService.fetchCustomerList();
    }

    @GetMapping("/get/{customer_id}")
    @Operation(summary = "Get customer by ID")
    public ResponseEntity<Customer> findCustomerById(@PathVariable("customer_id") UUID customer_id) {
        Customer customer = customerService.findCustomerById(customer_id);
        return ResponseEntity.ok().body(customer);
    }

    @PostMapping("/save")
    @Operation(summary = "Save a new customer")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/update/{customer_id}")
    @Operation(summary = "Update customer by ID")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("customer_id") UUID customer_id, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(customer_id, customer);
        return ResponseEntity.ok().body(updatedCustomer);
    }

    @DeleteMapping("/delete/{customer_id}")
    @Operation(summary = "Delete customer by ID")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable("customer_id") UUID customer_id) {
        customerService.deleteCustomerById(customer_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}