package org.project.InventoryManagementSystem.controller;

import java.util.List;
import java.util.UUID;
import org.project.InventoryManagementSystem.entity.Product;
import org.project.InventoryManagementSystem.service.ProductService;
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
@RequestMapping({"/products"})
public class ProductController {
    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping({"/get"})
    public List<Product> fetchProductList() {
        return this.productService.fetchProductList();
    }

    @GetMapping({"/get/{product_id}"})
    public ResponseEntity<Product> getProductById(@PathVariable("product_id") UUID product_id) {
        Product product = this.productService.getProductById(product_id);
        return ResponseEntity.ok().body(product);
    }

    @PostMapping({"/save"})
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product savedProduct = this.productService.saveProduct(product);
        return new ResponseEntity(savedProduct, HttpStatus.CREATED);
    }

    @PutMapping({"/update/{product_id}"})
    public ResponseEntity<Product> updateProduct(@PathVariable("product_id") UUID product_id, @RequestBody Product product) {
        Product updatedProduct = this.productService.updateProduct(product_id, product);
        return ResponseEntity.ok().body(updatedProduct);
    }

    @DeleteMapping({"/delete/{product_id}"})
    public ResponseEntity<Void> deleteProductById(@PathVariable("product_id") UUID product_id) {
        this.productService.deleteProductById(product_id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
