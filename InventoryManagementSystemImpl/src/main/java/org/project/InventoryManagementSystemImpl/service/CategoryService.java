package org.project.InventoryManagementSystemImpl.service;

import org.project.InventoryManagementSystemImpl.entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    Category saveCategory(Category category);

    List<Category> fetchCategoryList();

    Category updateCategoryById(UUID category_id, Category category);

    void deleteCategoryById(UUID category_id);

    Category findCategoryById(UUID category_id);
}
