package org.project.InventoryManagementSystemImpl.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.project.InventoryManagementSystemImpl.entity.Product;
import org.project.InventoryManagementSystemImpl.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@Tag(name = "Product", description = "Product management APIs")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/get")
    @Operation(summary = "Get all products")
    public List<Product> fetchProductList() {
        return productService.fetchProductList();
    }

    @GetMapping("/get/{product_id}")
    @Operation(summary = "Get product by ID")
    public ResponseEntity<Product> findProductById(@PathVariable("product_id") UUID product_id) {
        Product product = productService.findProductById(product_id);
        return ResponseEntity.ok().body(product);
    }
    @PostMapping("/save")
    @Operation(summary = "Save a new product")
    public ResponseEntity<Product> saveProduct (@RequestBody Product product){
        Product savedProduct = productService.saveProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @PutMapping("/update/{product_id}")
    @Operation(summary = "Update product by ID")
    public ResponseEntity<Product> updateProduct (@PathVariable("product_id") UUID product_id, @RequestBody Product product){
        Product updatedProduct = productService.updateProduct(product_id, product);
        return ResponseEntity.ok().body(updatedProduct);
    }

    @DeleteMapping("/delete/{product_id}")
    @Operation(summary = "Delete product by ID")
    public ResponseEntity<Void> deleteProductById (@PathVariable("product_id") UUID product_id){
        productService.deleteProductById(product_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}