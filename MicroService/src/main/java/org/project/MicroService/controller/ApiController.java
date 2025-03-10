package org.project.MicroService.controller;

import org.project.MicroService.entity.Category;
import org.project.MicroService.entity.Customer;
import org.project.MicroService.entity.OrdersPlaced;
import org.project.MicroService.entity.Product;
import org.project.MicroService.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/categories/get/{id}")
    public Category findCategoryById(@PathVariable("id") UUID category_id) {
        return apiService.findCategoryById(category_id);
    }

    @GetMapping("/products/get/{product_id}")
    public Product findProductById(@PathVariable("product_id") UUID product_id) {
        return apiService.findProductById(product_id);
    }

    @GetMapping("/customers/get/{customer_id}")
    public Customer findCustomerById(@PathVariable("customer_id") UUID customer_id) {
        return apiService.findCustomerById(customer_id);
    }

    @GetMapping("/orders/get/{order_id}")
    public OrdersPlaced getOrderById(@PathVariable("order_id") UUID order_id) {
        return apiService.getOrderById(order_id);
    }
}
