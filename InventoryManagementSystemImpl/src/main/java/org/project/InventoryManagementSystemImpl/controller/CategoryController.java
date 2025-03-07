package org.project.InventoryManagementSystemImpl.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.project.InventoryManagementSystemImpl.entity.Category;
import org.project.InventoryManagementSystemImpl.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
@Tag(name = "Category", description = "Category management APIs")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/get")
    @Operation(summary = "Get all categories")
    public List<Category> getAllCategories() {
        return categoryService.fetchCategoryList();
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Get category by ID")
    public ResponseEntity<Category> findCategoryById(@PathVariable("id") UUID category_id) {
        Category category = categoryService.findCategoryById(category_id);
        return ResponseEntity.ok().body(category);
    }

    @PostMapping("/save")
    @Operation(summary = "Save a new category")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
        Category savedCategory = categoryService.saveCategory(category);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update category by ID")
    public ResponseEntity<Category> updateCategoryById(@PathVariable("id") UUID category_id, @RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategoryById(category_id, category);
        return ResponseEntity.ok().body(updatedCategory);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete category by ID")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable("id") UUID category_id) {
        categoryService.deleteCategoryById(category_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}