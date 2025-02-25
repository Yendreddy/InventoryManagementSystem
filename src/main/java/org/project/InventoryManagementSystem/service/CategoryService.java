package org.project.InventoryManagementSystem.service;

import org.project.InventoryManagementSystem.dto.CategoryDTO;
import org.project.InventoryManagementSystem.exception.CategoryNotFoundException;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    /**
     * Saves a new category.
     *
     * @param categoryDTO The {@link CategoryDTO} object representing the category to save.
     * @return true if the category is saved successfully, false otherwise.
     */
    boolean saveCategory(CategoryDTO categoryDTO);

    /**
     * Fetches the list of all categories.
     *
     * @return A list of {@link CategoryDTO} objects representing all categories.
     */
    List<CategoryDTO> fetchCategoryList();

    /**
     * Updates an existing category by its ID.
     *
     * @param category_id The UUID of the category to update.
     * @param categoryDTO The {@link CategoryDTO} object representing the updated category.
     * @return true if the category is updated successfully, false otherwise.
     * @throws CategoryNotFoundException if the category is not found.
     */
    boolean updateCategoryById(UUID category_id, CategoryDTO categoryDTO);

    /**
     * Deletes a category by its ID.
     *
     * @param category_id The UUID of the category to delete.
     * @return true if the category is deleted successfully, false otherwise.
     * @throws CategoryNotFoundException if the category is not found.
     */
    boolean deleteCategoryById(UUID category_id);

    /**
     * Finds a category by its ID.
     *
     * @param category_id The UUID of the category to find.
     * @return A {@link CategoryDTO} object representing the found category.
     * @throws CategoryNotFoundException if the category is not found.
     */
    CategoryDTO findCategoryById(UUID category_id);
}
