package org.project.InventoryManagementSystem.controller;

import java.util.List;
import java.util.UUID;
import lombok.Generated;
import org.project.InventoryManagementSystem.entity.Customer;
import org.project.InventoryManagementSystem.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/customers"})
public class CustomerController {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private CustomerService customerService;

    public CustomerController() {
    }

    @GetMapping({"/get"})
    public List<Customer> getAllCustomers() {
        return this.customerService.fetchCustomerList();
    }

    @GetMapping({"/get/{customer_id}"})
    public ResponseEntity<Customer> getCustomerById(@PathVariable("customer_id") UUID customer_id) {
        Customer customer = this.customerService.getCustomerById(customer_id);
        return ResponseEntity.ok().body(customer);
    }

    @PostMapping({"/save"})
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = this.customerService.saveCustomer(customer);
        return new ResponseEntity(savedCustomer, HttpStatus.CREATED);
    }

    @PutMapping({"/update/{customer_id}"})
    public ResponseEntity<Customer> updateCustomer(@PathVariable("customer_id") UUID customer_id, @RequestBody Customer customer) {
        Customer updatedCustomer = this.customerService.updateCustomer(customer_id, customer);
        return ResponseEntity.ok().body(updatedCustomer);
    }

    @DeleteMapping({"/delete/{customer_id}"})
    public ResponseEntity<Void> deleteCustomerById(@PathVariable("customer_id") UUID customer_id) {
        this.customerService.deleteCustomerById(customer_id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
