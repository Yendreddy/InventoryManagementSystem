package org.project.InventoryManagementSystem.controller;

import org.project.InventoryManagementSystem.dto.CategoryDTO;
import org.project.InventoryManagementSystem.entity.Category;
import org.project.InventoryManagementSystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/categories")
    public Category saveCategory(@RequestBody Category category){
        return categoryService.saveCategory(category);
    }

    @GetMapping("/categories")
    public List<Category> fetchCategoryList(){
        return categoryService.fetchCategoryList();
    }

    @GetMapping("/categories/{id}")
    public Category getCategoryById(@PathVariable("id") UUID category_id) {
        return categoryService.findCategoryById(category_id);
    }

    @PutMapping("/categories/{id}")
    public Category updateCategoryById(@PathVariable("id")UUID category_id, @RequestBody Category category){
        return categoryService.updateCategoryById(category_id,category);
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategoryById(@PathVariable("id")UUID category_id){
        categoryService.deleteCategoryById(category_id);
    }
}
