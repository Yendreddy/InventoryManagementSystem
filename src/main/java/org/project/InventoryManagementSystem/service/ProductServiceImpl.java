package org.project.InventoryManagementSystem.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.modelmapper.ModelMapper;
import org.project.InventoryManagementSystem.dto.ProductDTO;
import org.project.InventoryManagementSystem.entity.Category;
import org.project.InventoryManagementSystem.entity.Product;
import org.project.InventoryManagementSystem.exception.ProductNotFoundException;
import org.project.InventoryManagementSystem.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final SessionFactory sessionFactory;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ModelMapper modelMapper) {
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(Product.class);
        cfg.addAnnotatedClass(Category.class);
        cfg.configure();
        this.sessionFactory = cfg.buildSessionFactory();
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductDTO> fetchProductList() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Product> products = session.createQuery("FROM Product", Product.class).list();
            session.getTransaction().commit();
            return products.stream()
                    .map(product -> modelMapper.map(product, ProductDTO.class))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public ProductDTO getProductById(UUID product_id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, product_id);
            session.getTransaction().commit();
            if (product != null) {
                return modelMapper.map(product, ProductDTO.class);
            } else {
                throw new ProductNotFoundException("Product", "id", product_id);
            }
        }
    }

    @Override
    public boolean saveProduct(ProductDTO productDTO) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Product product = modelMapper.map(productDTO, Product.class);
            product.setCategory(session.get(Category.class, productDTO.getCategory_id()));
            session.save(product);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateProduct(UUID product_id, ProductDTO productDTO) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Product existingProduct = session.get(Product.class, product_id);
            if (existingProduct != null) {
                existingProduct.setName(productDTO.getName());
                existingProduct.setPrice(productDTO.getPrice());
                existingProduct.setQuantity(productDTO.getQuantity());
                existingProduct.setCategory(modelMapper.map(productDTO.getCategory_id(), Category.class));
                session.merge(existingProduct);
                session.getTransaction().commit();
                return true;
            } else {
                throw new ProductNotFoundException("Product", "id", product_id);
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteProductById(UUID product_id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, product_id);
            if (product != null) {
                session.delete(product);
                session.getTransaction().commit();
                return true;
            } else {
                throw new ProductNotFoundException("Product", "id", product_id);
            }
        } catch (Exception e) {
            return false;
        }
    }
}
