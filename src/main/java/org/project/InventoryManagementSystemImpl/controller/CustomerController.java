package org.project.InventoryManagementSystemImpl.controller;

import org.modelmapper.ModelMapper;
import org.project.InventoryManagementSystemImpl.dto.CustomerDTO;
import org.project.InventoryManagementSystemImpl.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping("/get")
    public List<CustomerDTO> getAllCustomers() {
        return customerService.fetchCustomerList();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("id") UUID customer_id) {
        CustomerDTO customerDTO = customerService.getCustomerById(customer_id);
        return ResponseEntity.ok().body(customerDTO);
    }

    @PostMapping("/save")
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        boolean isCreated = customerService.saveCustomer(customerDTO);
        if (isCreated) {
            return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("id") UUID customer_id, @RequestBody CustomerDTO customerDTO) {
        boolean isUpdated = customerService.updateCustomer(customer_id, customerDTO);
        if (isUpdated) {
            return ResponseEntity.ok().body(customerDTO);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable("id") UUID customer_id) {
        boolean isDeleted = customerService.deleteCustomerById(customer_id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}