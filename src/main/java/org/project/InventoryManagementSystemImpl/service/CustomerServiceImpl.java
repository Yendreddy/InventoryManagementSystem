package org.project.InventoryManagementSystemImpl.service;

import org.modelmapper.ModelMapper;
import org.project.InventoryManagementSystemImpl.dto.CustomerDTO;
import org.project.InventoryManagementSystemImpl.entity.Customer;
import org.project.InventoryManagementSystemImpl.exception.CustomerNotFoundException;
import org.project.InventoryManagementSystemImpl.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CustomerDTO> fetchCustomerList() {
        return customerRepository.findAll().stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(UUID customer_id) {
        Customer customer = customerRepository.findById(customer_id)
                .orElseThrow(() -> new CustomerNotFoundException(customer_id));
        return modelMapper.map(customer, CustomerDTO.class);
    }

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        customerRepository.save(customer);
        return true;
    }

    @Override
    public boolean updateCustomer(UUID customer_id, CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findById(customer_id)
                .orElseThrow(() -> new CustomerNotFoundException(customer_id));
        existingCustomer.setName(customerDTO.getName());
        existingCustomer.setPhone_number(customerDTO.getPhone_number());
        existingCustomer.setEmail_id(customerDTO.getEmail_id());
        customerRepository.save(existingCustomer);
        return true;
    }

    @Override
    public boolean deleteCustomerById(UUID customer_id) {
        Customer customer = customerRepository.findById(customer_id)
                .orElseThrow(() -> new CustomerNotFoundException(customer_id));
        customerRepository.delete(customer);
        return true;
    }
}
