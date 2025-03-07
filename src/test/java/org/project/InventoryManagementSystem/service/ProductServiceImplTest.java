package org.project.InventoryManagementSystem.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project.InventoryManagementSystem.entity.Product;
import org.project.InventoryManagementSystem.exception.ProductNotFoundException;
import org.project.InventoryManagementSystem.repository.ProductRepository;

@ExtendWith({MockitoExtension.class})
public class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImpl productService;
    private Product product;
    private UUID productId;

    public ProductServiceImplTest() {
    }

    @BeforeEach
    public void setUp() {
        this.productId = UUID.randomUUID();
        this.product = new Product();
        this.product.setProduct_id(this.productId);
        this.product.setName("Laptop");
        this.product.setPrice(1000);
        this.product.setQuantity(10);
    }

    @Test
    public void testFetchProductList() {
        List<Product> products = Arrays.asList(this.product);
        Mockito.when(this.productRepository.findAll()).thenReturn(products);
        List<Product> fetchedProducts = this.productService.fetchProductList();
        Assertions.assertEquals(1, fetchedProducts.size());
        ((ProductRepository)Mockito.verify(this.productRepository, Mockito.times(1))).findAll();
    }

    @Test
    public void testGetProductById() {
        Mockito.when(this.productRepository.findById(this.productId)).thenReturn(Optional.of(this.product));
        Product fetchedProduct = this.productService.getProductById(this.productId);
        Assertions.assertNotNull(fetchedProduct);
        Assertions.assertEquals("Laptop", fetchedProduct.getName());
        ((ProductRepository)Mockito.verify(this.productRepository, Mockito.times(1))).findById(this.productId);
    }

    @Test
    public void testGetProductById_NotFound() {
        Mockito.when(this.productRepository.findById(this.productId)).thenReturn(Optional.empty());
        Assertions.assertThrows(ProductNotFoundException.class, () -> this.productService.getProductById(this.productId));
        ((ProductRepository)Mockito.verify(this.productRepository, Mockito.times(1))).findById(this.productId);
    }

    @Test
    public void testSaveProduct() {
        Mockito.when((Product)this.productRepository.save(this.product)).thenReturn(this.product);
        Product savedProduct = this.productService.saveProduct(this.product);
        Assertions.assertNotNull(savedProduct);
        Assertions.assertEquals("Laptop", savedProduct.getName());
        ((ProductRepository)Mockito.verify(this.productRepository, Mockito.times(1))).save(this.product);
    }

    @Test
    public void testUpdateProduct() {
        Mockito.when(this.productRepository.findById(this.productId)).thenReturn(Optional.of(this.product));
        Mockito.when((Product)this.productRepository.save(this.product)).thenReturn(this.product);
        this.product.setName("Updated Laptop");
        Product updatedProduct = this.productService.updateProduct(this.productId, this.product);
        Assertions.assertNotNull(updatedProduct);
        Assertions.assertEquals("Updated Laptop", updatedProduct.getName());
        ((ProductRepository)Mockito.verify(this.productRepository, Mockito.times(1))).findById(this.productId);
        ((ProductRepository)Mockito.verify(this.productRepository, Mockito.times(1))).save(this.product);
    }

    @Test
    public void testUpdateProduct_NotFound() {
        Mockito.when(this.productRepository.findById(this.productId)).thenReturn(Optional.empty());
        Assertions.assertThrows(ProductNotFoundException.class, () -> this.productService.updateProduct(this.productId, this.product));
        ((ProductRepository)Mockito.verify(this.productRepository, Mockito.times(1))).findById(this.productId);
    }

    @Test
    public void testDeleteProductById() {
        Mockito.when(this.productRepository.findById(this.productId)).thenReturn(Optional.of(this.product));
        ((ProductRepository)Mockito.doNothing().when(this.productRepository)).delete(this.product);
        this.productService.deleteProductById(this.productId);
        ((ProductRepository)Mockito.verify(this.productRepository, Mockito.times(1))).findById(this.productId);
        ((ProductRepository)Mockito.verify(this.productRepository, Mockito.times(1))).delete(this.product);
    }

    @Test
    public void testDeleteProductById_NotFound() {
        Mockito.when(this.productRepository.findById(this.productId)).thenReturn(Optional.empty());
        Assertions.assertThrows(ProductNotFoundException.class, () -> this.productService.deleteProductById(this.productId));
        ((ProductRepository)Mockito.verify(this.productRepository, Mockito.times(1))).findById(this.productId);
    }
}
