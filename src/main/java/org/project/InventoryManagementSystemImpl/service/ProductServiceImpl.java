package org.project.InventoryManagementSystemImpl.service;

import org.modelmapper.ModelMapper;
import org.project.InventoryManagementSystemImpl.dto.ProductDTO;
import org.project.InventoryManagementSystemImpl.entity.Category;
import org.project.InventoryManagementSystemImpl.entity.Product;
import org.project.InventoryManagementSystemImpl.exception.ProductNotFoundException;
import org.project.InventoryManagementSystemImpl.repository.CategoryRepository;
import org.project.InventoryManagementSystemImpl.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProductDTO> fetchProductList() {
        return productRepository.findAll().stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(UUID product_id) {
        Product product = productRepository.findById(product_id)
                .orElseThrow(() -> new ProductNotFoundException(product_id));
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public boolean saveProduct(ProductDTO productDTO) {
        try {
            Product product = modelMapper.map(productDTO, Product.class);
            Category category = categoryRepository.findById(productDTO.getCategory_id())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateProduct(UUID product_id, ProductDTO productDTO) {
        try {
            Product existingProduct = productRepository.findById(product_id)
                    .orElseThrow(() -> new ProductNotFoundException(product_id));

            existingProduct.setName(productDTO.getName());
            existingProduct.setPrice(productDTO.getPrice());
            existingProduct.setQuantity(productDTO.getQuantity());

            Category category = categoryRepository.findById(productDTO.getCategory_id())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            existingProduct.setCategory(category);

            productRepository.save(existingProduct);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteProductById(UUID product_id) {
        try {
            Product product = productRepository.findById(product_id)
                    .orElseThrow(() -> new ProductNotFoundException(product_id));
            productRepository.delete(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
