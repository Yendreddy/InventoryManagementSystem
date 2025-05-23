package org.project.InventoryManagementSystemImpl.controller;

import org.modelmapper.ModelMapper;
import org.project.InventoryManagementSystemImpl.dto.ProductDTO;
import org.project.InventoryManagementSystemImpl.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> fetchProductList() {
        return productService.fetchProductList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") UUID product_id) {
        ProductDTO productDTO = productService.getProductById(product_id);
        return ResponseEntity.ok().body(productDTO);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productDTO) {
        boolean isCreated = productService.saveProduct(productDTO);
        if (isCreated) {
            return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") UUID product_id, @RequestBody ProductDTO productDTO) {
        boolean isUpdated = productService.updateProduct(product_id, productDTO);
        if (isUpdated) {
            return ResponseEntity.ok().body(productDTO);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable("id") UUID product_id) {
        boolean isDeleted = productService.deleteProductById(product_id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}