package org.project.InventoryManagementSystemImpl.service;

import org.project.InventoryManagementSystemImpl.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product saveProduct(Product product);

    List<Product> fetchProductList();

    Product updateProduct(UUID product_id, Product product);

    void deleteProductById(UUID product_id);

    Product findProductById(UUID product_id);
}
