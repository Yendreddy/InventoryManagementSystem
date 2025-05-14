package org.project.InventoryManagementSystemImpl.service;

import org.project.InventoryManagementSystemImpl.entity.Product;
import org.project.InventoryManagementSystemImpl.exception.ProductNotFoundException;
import org.project.InventoryManagementSystemImpl.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> fetchProductList() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(UUID product_id, Product product) {
        return productRepository.findById(product_id)
                .map(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setPrice(product.getPrice());
                    existingProduct.setQuantity(product.getQuantity());
                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new ProductNotFoundException("Product not found" + product_id));
    }

    @Override
    public void deleteProductById(UUID product_id) {
        Product product = productRepository.findById(product_id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found" + product_id));
        productRepository.delete(product);
    }

    @Override
    public Product findProductById(UUID product_id) {
        return productRepository.findById(product_id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found" + product_id));
    }
}
