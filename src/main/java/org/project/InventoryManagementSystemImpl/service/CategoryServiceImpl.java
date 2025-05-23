package org.project.InventoryManagementSystemImpl.service;

import org.modelmapper.ModelMapper;
import org.project.InventoryManagementSystemImpl.dto.CategoryDTO;
import org.project.InventoryManagementSystemImpl.entity.Category;
import org.project.InventoryManagementSystemImpl.exception.CategoryNotFoundException;
import org.project.InventoryManagementSystemImpl.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoryDTO> fetchCategoryList() {
        return categoryRepository.findAll().stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findCategoryById(UUID category_id) {
        Category category = categoryRepository.findById(category_id)
                .orElseThrow(() -> new CategoryNotFoundException(category_id));
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public boolean saveCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        categoryRepository.save(category);
        return true;
    }

    @Override
    public boolean updateCategoryById(UUID category_id, CategoryDTO categoryDTO) {
        Category existingCategory = categoryRepository.findById(category_id)
                .orElseThrow(() -> new CategoryNotFoundException(category_id));
        existingCategory.setName(categoryDTO.getName());
        categoryRepository.save(existingCategory);
        return true;
    }

    @Override
    public boolean deleteCategoryById(UUID category_id) {
        Category category = categoryRepository.findById(category_id)
                .orElseThrow(() -> new CategoryNotFoundException(category_id));
        categoryRepository.delete(category);
        return true;
    }
}
