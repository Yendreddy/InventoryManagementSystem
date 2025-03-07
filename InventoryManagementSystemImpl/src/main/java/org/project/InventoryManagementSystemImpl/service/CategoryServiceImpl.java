package org.project.InventoryManagementSystemImpl.service;

import org.project.InventoryManagementSystemImpl.entity.Category;
import org.project.InventoryManagementSystemImpl.exception.CategoryNotFoundException;
import org.project.InventoryManagementSystemImpl.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> fetchCategoryList() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategoryById(UUID category_id, Category category) {
        return categoryRepository.findById(category_id)
                .map(existingCategory -> {
                    existingCategory.setName(category.getName());
                    return categoryRepository.save(existingCategory);
                })
                .orElseThrow(() -> new CategoryNotFoundException("Category not found" + category_id));
    }

    @Override
    public void deleteCategoryById(UUID category_id) {
        Category category = categoryRepository.findById(category_id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found" + category_id));
        categoryRepository.delete(category);
    }

    @Override
    public Category findCategoryById(UUID category_id) {
        return categoryRepository.findById(category_id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found" + category_id));
    }
}
