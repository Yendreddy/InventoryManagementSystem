package org.project.InventoryManagementSystem.service;

import org.project.InventoryManagementSystem.entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    Category saveCategory(Category category);

    List<Category> fetchCategoryList();

    Category updateCategoryById(UUID category_id, Category category);

    void deleteCategoryById(UUID category_id);

    Category findCategoryById(UUID category_id);
}
