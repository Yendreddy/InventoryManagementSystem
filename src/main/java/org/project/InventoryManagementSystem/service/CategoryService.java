package org.project.InventoryManagementSystem.service;

import org.project.InventoryManagementSystem.dto.CategoryDTO;
import org.project.InventoryManagementSystem.entity.Category;
import org.project.InventoryManagementSystem.exception.CategoryNotFoundException;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    /**
     * Saves a new category.
     *
     * @param category The {@link Category} object representing the category to save.
     * @return true if the category is saved successfully, false otherwise.
     */
    Category saveCategory(Category category);

    /**
     * Fetches the list of all categories.
     *
     * @return A list of {@link Category} objects representing all categories.
     */
    List<Category> fetchCategoryList();

    /**
     * Updates an existing category by its ID.
     *
     * @param category_id The UUID of the category to update.
     * @param category The {@link Category} object representing the updated category.
     * @return Category.
     * @throws CategoryNotFoundException if the category is not found.
     */
    Category updateCategoryById(UUID category_id, Category category);

    /**
     * Deletes a category by its ID.
     *
     * @param category_id The UUID of the category to delete.
     *
     * @throws CategoryNotFoundException if the category is not found.
     */
    void deleteCategoryById(UUID category_id);

    /**
     * Finds a category by its ID.
     *
     * @param category_id The UUID of the category to find.
     * @return A {@link Category} object representing the found category.
     * @throws CategoryNotFoundException if the category is not found.
     */
    Category findCategoryById(UUID category_id);
}
