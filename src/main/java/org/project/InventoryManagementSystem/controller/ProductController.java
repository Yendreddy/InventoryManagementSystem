package org.project.InventoryManagementSystem.controller;

import org.project.InventoryManagementSystem.entity.Product;
import org.project.InventoryManagementSystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public Product saveProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    @GetMapping("/products")
    public List<Product> fetchProductList(){
        return productService.fetchProductList();
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") UUID productId, @RequestBody Product product){
        return productService.updateProduct(productId,product);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProductById(@PathVariable("id") UUID productId){
        productService.deleteCustomerById(productId);
    }
}
