package org.project.MicroService.service;

import org.project.MicroService.entity.Category;
import org.project.MicroService.entity.Customer;
import org.project.MicroService.entity.OrdersPlaced;
import org.project.MicroService.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class ApiService {

    @Autowired
    private RestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8090";

    public Category findCategoryById(UUID category_id) {
        String url = BASE_URL + "/categories/" +"get/" + category_id;
        return restTemplate.getForObject(url, Category.class);
    }

    public Product findProductById(@PathVariable("product_id") UUID product_id) {
        String url = BASE_URL + "/products/" + "get/" + product_id;
        return restTemplate.getForObject(url, Product.class);
    }

    public Customer findCustomerById(@PathVariable("customer_id") UUID customer_id) {
        String url = BASE_URL + "/customers/" + "get/" + customer_id;
        return restTemplate.getForObject(url, Customer.class);
    }

    public OrdersPlaced getOrderById(@PathVariable("order_id") UUID order_id) {
        String url = BASE_URL + "/orders/" +"get/" + order_id;
        return restTemplate.getForObject(url, OrdersPlaced.class);
    }
}
