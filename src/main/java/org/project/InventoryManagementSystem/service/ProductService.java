package org.project.InventoryManagementSystem.service;

import org.project.InventoryManagementSystem.dto.CustomerDTO;
import org.project.InventoryManagementSystem.dto.OrdersPlacedDTO;
import org.project.InventoryManagementSystem.dto.ProductDTO;
import org.project.InventoryManagementSystem.exception.CustomerNotFoundException;
import org.project.InventoryManagementSystem.exception.ProductNotFoundException;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    /**
     * Saves a new product.
     *
     * @param productDTO The {@link ProductDTO} object representing the product to save.
     * @return true if the product is saved successfully, false otherwise.
     */
    boolean saveProduct(ProductDTO productDTO);

    /**
     * Fetches the list of all products.
     *
     * @return A list of {@link ProductDTO} objects representing all products.
     */
    List<ProductDTO> fetchProductList();

    /**
     * Updates an existing product by its ID.
     *
     * @param product_id The UUID of the product to update.
     * The {@link ProductDTO} object representing the updated product.
     * @return true if the product is updated successfully, false otherwise.
     * @throws ProductNotFoundException if the product is not found.
     */
    boolean updateProduct(UUID product_id, ProductDTO productDTO);

    /**
     * Deletes a product by its ID.
     *
     * @param product_id The UUID of the product to delete.
     * @return true if the product is deleted successfully, false otherwise.
     * @throws ProductNotFoundException if the product is not found.
     */
    boolean deleteProductById(UUID product_id);

    /**
     * Finds a product by its ID.
     *
     * @param product_id The UUID of the product to find.
     * @return A {@link ProductDTO} object representing the found product.
     * @throws ProductNotFoundException if the product is not found.
     */
    ProductDTO getProductById(UUID product_id);
}
