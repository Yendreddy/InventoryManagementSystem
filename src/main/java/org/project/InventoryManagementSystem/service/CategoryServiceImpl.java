package org.project.InventoryManagementSystem.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.modelmapper.ModelMapper;
import org.project.InventoryManagementSystem.dto.CategoryDTO;
import org.project.InventoryManagementSystem.entity.Category;
import org.project.InventoryManagementSystem.exception.CategoryNotFoundException;
import org.project.InventoryManagementSystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private ModelMapper modelMapper;

    public CategoryServiceImpl(ModelMapper modelMapper) {
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(Category.class);
        cfg.configure();
        this.sessionFactory = cfg.buildSessionFactory();
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CategoryDTO> fetchCategoryList() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Category> categories = session.createQuery("FROM Category", Category.class).list();
            session.getTransaction().commit();
            return categories.stream()
                    .map(category -> modelMapper.map(category, CategoryDTO.class))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public CategoryDTO findCategoryById(UUID category_id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Category category = session.get(Category.class, category_id);
            session.getTransaction().commit();
            if (category != null) {
                return modelMapper.map(category, CategoryDTO.class);
            } else {
                throw new CategoryNotFoundException("Category", "id", category_id);
            }
        }
    }

    @Override
    public boolean saveCategory(CategoryDTO categoryDTO) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Category category = modelMapper.map(categoryDTO, Category.class);
            session.save(category);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateCategoryById(UUID category_id, CategoryDTO categoryDTO) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Category existingCategory = session.get(Category.class, category_id);
            if (existingCategory != null) {
                existingCategory.setName(categoryDTO.getName());
                session.merge(existingCategory);
                session.getTransaction().commit();
                return true;
            } else {
                throw new CategoryNotFoundException("Category", "id", category_id);
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteCategoryById(UUID category_id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Category category = session.get(Category.class, category_id);
            if (category != null) {
                session.delete(category);
                session.getTransaction().commit();
                return true;
            } else {
                throw new CategoryNotFoundException("Category", "id", category_id);
            }
        } catch (Exception e) {
            return false;
        }
    }
}