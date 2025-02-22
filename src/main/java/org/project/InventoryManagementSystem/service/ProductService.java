package org.project.InventoryManagementSystem.service;

import org.project.InventoryManagementSystem.dto.ProductDTO;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    boolean saveProduct(ProductDTO productDTO);

    List<ProductDTO> fetchProductList();

    boolean updateProduct(UUID product_id, ProductDTO productDTO);

    boolean deleteProductById(UUID product_id);

    ProductDTO getProductById(UUID product_id);
}
