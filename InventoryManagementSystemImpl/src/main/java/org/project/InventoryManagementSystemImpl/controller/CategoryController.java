package org.project.InventoryManagementSystemImpl.controller;

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
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/get")
    public List<Category> getAllCategories() {
        return categoryService.fetchCategoryList();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable("id") UUID category_id) {
        Category category = categoryService.findCategoryById(category_id);
        return ResponseEntity.ok().body(category);
    }

    @PostMapping("/save")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
        Category savedCategory = categoryService.saveCategory(category);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Category> updateCategoryById(@PathVariable("id") UUID category_id, @RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategoryById(category_id, category);
        return ResponseEntity.ok().body(updatedCategory);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable("id") UUID category_id) {
        categoryService.deleteCategoryById(category_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}