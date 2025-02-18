package org.project.InventoryManagementSystem.controller;

import jakarta.validation.Valid;
import org.project.InventoryManagementSystem.entity.Customer;
import org.project.InventoryManagementSystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customers")
    public Customer saveCustomer( @RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/customers")
    public List<Customer> fetchCustomerList(){
        return customerService.fetchCustomerList();
    }

    @PutMapping("/customers/{id}")
    public Customer updateCustomer(@PathVariable("id") UUID customerId, @RequestBody Customer customer){
        return customerService.updateCustomer(customerId,customer);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomerById(@PathVariable("id") UUID customerId){
        customerService.deleteCustomerById(customerId);
    }
}
