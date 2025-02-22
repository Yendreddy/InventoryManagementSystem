package org.project.InventoryManagementSystem.service;

import org.project.InventoryManagementSystem.dto.CategoryDTO;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    boolean saveCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> fetchCategoryList();

    boolean updateCategoryById(UUID category_id, CategoryDTO categoryDTO);

    boolean deleteCategoryById(UUID category_id);

    CategoryDTO findCategoryById(UUID category_id);
}
