package org.project.InventoryManagementSystem.controller;

import java.util.List;
import java.util.UUID;
import org.project.InventoryManagementSystem.entity.Category;
import org.project.InventoryManagementSystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/categories"})
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    public CategoryController() {
    }

    @GetMapping({"/get"})
    public List<Category> getAllCategories() {
        return this.categoryService.fetchCategoryList();
    }

    @GetMapping({"/get/{category_id}"})
    public ResponseEntity<Category> findCategoryById(@PathVariable("category_id") UUID category_id) {
        Category category = this.categoryService.findCategoryById(category_id);
        return ResponseEntity.ok().body(category);
    }

    @PostMapping({"/save"})
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
        Category savedCategory = this.categoryService.saveCategory(category);
        return new ResponseEntity(savedCategory, HttpStatus.CREATED);
    }

    @PutMapping({"/update/{category_id}"})
    public ResponseEntity<Category> updateCategoryById(@PathVariable("category_id") UUID category_id, @RequestBody Category category) {
        Category updatedCategory = this.categoryService.updateCategoryById(category_id, category);
        return ResponseEntity.ok().body(updatedCategory);
    }

    @DeleteMapping({"/delete/{category_id}"})
    public ResponseEntity<Void> deleteCategoryById(@PathVariable("category_id") UUID category_id) {
        this.categoryService.deleteCategoryById(category_id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
