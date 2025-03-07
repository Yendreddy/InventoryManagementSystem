package org.project.InventoryManagementSystem.service;

import java.util.List;
import java.util.UUID;
import org.project.InventoryManagementSystem.entity.Category;
import org.project.InventoryManagementSystem.exception.CategoryNotFoundException;
import org.project.InventoryManagementSystem.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

        @Autowired
        private CategoryRepository categoryRepository;

        @Cacheable({"categories"})
        public List<Category> fetchCategoryList() {
            return this.categoryRepository.findAll();
        }

        @Cacheable(
                value = {"categories"},
                key = "#category_id"
        )
        public Category findCategoryById(UUID category_id) {
            return (Category)this.categoryRepository.findById(category_id).orElseThrow(() -> new CategoryNotFoundException("Category", "id", category_id));
        }

        @CacheEvict(
                value = {"categories"},
                allEntries = true
        )
        public Category saveCategory(Category category) {
            return categoryRepository.save(category);
        }

        @CacheEvict(
                value = {"categories"},
                key = "#category_id"
        )
        public Category updateCategoryById(UUID category_id, Category category) {
            Category existingCategory = (Category)this.categoryRepository.findById(category_id).orElseThrow(() -> new CategoryNotFoundException("Category", "id", category_id));
            existingCategory.setName(category.getName());
            return (Category)this.categoryRepository.save(existingCategory);
        }

        @CacheEvict(
                value = {"categories"},
                key = "#category_id"
        )
        public void deleteCategoryById(UUID category_id) {
            Category category = (Category)this.categoryRepository.findById(category_id).orElseThrow(() -> new CategoryNotFoundException("Category", "id", category_id));
            this.categoryRepository.delete(category);
        }
    }
