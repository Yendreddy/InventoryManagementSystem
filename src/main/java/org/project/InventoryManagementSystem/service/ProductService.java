package org.project.InventoryManagementSystem.service;

import org.project.InventoryManagementSystem.dto.ProductDTO;
import org.project.InventoryManagementSystem.entity.Product;
import org.project.InventoryManagementSystem.exception.ProductNotFoundException;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    /**
     * Saves a new product.
     *
     * @param product The {@link ProductDTO} object representing the product to save.
     * @return product.
     */
    Product saveProduct(Product product);

    /**
     * Fetches the list of all products.
     *
     * @return A list of {@link Product} objects representing all products.
     */
    List<Product> fetchProductList();

    /**
     * Updates an existing product by its ID.
     *
     * @param product_id The UUID of the product to update.
     * The {@link Product} object representing the updated product.
     * @return updated product.
     * @throws ProductNotFoundException if the product is not found.
     */
    Product updateProduct(UUID product_id, Product product);

    /**
     * Deletes a product by its ID.
     *
     * @param product_id The UUID of the product to delete.
     * @throws ProductNotFoundException if the product is not found.
     */
    void deleteProductById(UUID product_id);

    /**
     * Finds a product by its ID.
     *
     * @param product_id The UUID of the product to find.
     * @return A {@link Product} object representing the found product.
     * @throws ProductNotFoundException if the product is not found.
     */
    Product getProductById(UUID product_id);
}
