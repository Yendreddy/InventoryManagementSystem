package org.project.InventoryManagementSystem.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project.InventoryManagementSystem.entity.Product;
import org.project.InventoryManagementSystem.exception.ProductNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Transaction transaction;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    public void setUp() {
        product = Product.builder()
                .product_id(UUID.randomUUID())
                .name("Laptop")
                .price(1000)
                .quantity(10)
                .build();

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
    }

    @Test
    public void testGetAllProducts() {
        when(session.createQuery("FROM Product", Product.class).list()).thenReturn(Collections.singletonList(product));

        List<Product> products = productService.fetchProductList();

        assertNotNull(products);
        assertEquals(1, products.size());
        verify(session, times(1)).createQuery("FROM Product", Product.class);
    }

    @Test
    public void testSaveProduct() {
        Product savedProduct = productService.saveProduct(product);

        assertNotNull(savedProduct);
        verify(session, times(1)).save(product);
    }

    @Test
    public void testUpdateProductById() {
        when(session.get(Product.class, product.getProduct_id())).thenReturn(product);

        Product updatedProduct = productService.updateProduct(product.getProduct_id(), product);

        assertNotNull(updatedProduct);
        verify(session, times(1)).merge(product);
    }

    @Test
    public void testDeleteProductById() {
        when(session.get(Product.class, product.getProduct_id())).thenReturn(product);

        productService.deleteProductById(product.getProduct_id());

        verify(session, times(1)).delete(product);
    }

    @Test
    public void testGetProductById() {
        when(session.get(Product.class, product.getProduct_id())).thenReturn(product);

        Product foundProduct = productService.findProductById(product.getProduct_id());

        assertNotNull(foundProduct);
        verify(session, times(1)).get(Product.class, product.getProduct_id());
    }

    @Test
    public void testProductNotFoundException() {
        when(session.get(Product.class, product.getProduct_id())).thenReturn(null);

        assertThrows(ProductNotFoundException.class, () -> productService.findProductById(product.getProduct_id()));
    }
}