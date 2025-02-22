package org.project.InventoryManagementSystem.controller;

import org.modelmapper.ModelMapper;
import org.project.InventoryManagementSystem.dto.CategoryDTO;
import org.project.InventoryManagementSystem.service.CategoryService;
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
    private ModelMapper modelMapper;

    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return categoryService.fetchCategoryList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findCategoryById(@PathVariable("id") UUID category_id) {
        CategoryDTO categoryDTO = categoryService.findCategoryById(category_id);
        return ResponseEntity.ok().body(categoryDTO);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> saveCategory(@RequestBody CategoryDTO categoryDTO) {
        boolean isCreated = categoryService.saveCategory(categoryDTO);
        if (isCreated) {
            return new ResponseEntity<>(categoryDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategoryById(@PathVariable("id") UUID category_id, @RequestBody CategoryDTO categoryDTO) {
        boolean isUpdated = categoryService.updateCategoryById(category_id, categoryDTO);
        if (isUpdated) {
            return ResponseEntity.ok().body(categoryDTO);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable("id") UUID category_id) {
        boolean isDeleted = categoryService.deleteCategoryById(category_id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}