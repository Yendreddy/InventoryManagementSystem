package org.project.InventoryManagementSystem.controller;

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

    @PutMapping("/categories/{id}")
    public Category updateCategoryById(@PathVariable("id")UUID categoryId, @RequestBody Category category){
        return categoryService.updateCategoryById(categoryId,category);
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategoryById(@PathVariable("id")UUID categoryId){
        categoryService.deleteCategoryById(categoryId);
    }
}
