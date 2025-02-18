package org.project.InventoryManagementSystem.service;

import org.project.InventoryManagementSystem.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product saveProduct(Product product);

    List<Product> fetchProductList();

    Product updateProduct(UUID productId, Product product);

    void deleteCustomerById(UUID productId);
}
