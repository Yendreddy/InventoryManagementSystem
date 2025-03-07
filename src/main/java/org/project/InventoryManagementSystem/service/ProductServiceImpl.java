package org.project.InventoryManagementSystem.service;

import java.util.List;
import java.util.UUID;
import org.project.InventoryManagementSystem.entity.Product;
import org.project.InventoryManagementSystem.exception.ProductNotFoundException;
import org.project.InventoryManagementSystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductServiceImpl() {
    }

    @Cacheable({"products"})
    public List<Product> fetchProductList() {
        return this.productRepository.findAll();
    }

    @Cacheable(
            value = {"products"},
            key = "#product_id"
    )
    public Product getProductById(UUID product_id) {
        return (Product)this.productRepository.findById(product_id).orElseThrow(() -> new ProductNotFoundException("Product", "id", product_id));
    }

    @CacheEvict(
            value = {"products"},
            allEntries = true
    )
    public Product saveProduct(Product product) {
        return (Product)this.productRepository.save(product);
    }

    @CacheEvict(
            value = {"products"},
            key = "#product_id"
    )
    public Product updateProduct(UUID product_id, Product product) {
        Product existingProduct = (Product)this.productRepository.findById(product_id).orElseThrow(() -> new ProductNotFoundException("Product", "id", product_id));
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setCategory(product.getCategory());
        return (Product)this.productRepository.save(existingProduct);
    }

    @CacheEvict(
            value = {"products"},
            key = "#product_id"
    )
    public void deleteProductById(UUID product_id) {
        Product product = (Product)this.productRepository.findById(product_id).orElseThrow(() -> new ProductNotFoundException("Product", "id", product_id));
        this.productRepository.delete(product);
    }
}
