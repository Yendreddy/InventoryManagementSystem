package org.project.InventoryManagementSystem.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project.InventoryManagementSystem.entity.Category;
import org.project.InventoryManagementSystem.exception.CategoryNotFoundException;
import org.project.InventoryManagementSystem.repository.CategoryRepository;

@ExtendWith({MockitoExtension.class})
public class CategoryServiceImplTest {
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryServiceImpl categoryService;
    private Category category;
    private UUID categoryId;

    public CategoryServiceImplTest() {
    }

    @BeforeEach
    public void setUp() {
        this.categoryId = UUID.randomUUID();
        this.category = new Category();
        this.category.setCategory_id(this.categoryId);
        this.category.setName("Electronics");
    }

    @Test
    public void testFetchCategoryList() {
        List<Category> categories = Arrays.asList(this.category);
        Mockito.when(this.categoryRepository.findAll()).thenReturn(categories);
        List<Category> categories1 = this.categoryService.fetchCategoryList();
        Assertions.assertEquals(1, categories1.size());
        ((CategoryRepository)Mockito.verify(this.categoryRepository, Mockito.times(1))).findAll();
    }

    @Test
    public void testFindCategoryById() {
        Mockito.when(this.categoryRepository.findById(this.categoryId)).thenReturn(Optional.of(this.category));
        Category fetchedCategory = this.categoryService.findCategoryById(this.categoryId);
        Assertions.assertNotNull(fetchedCategory);
        Assertions.assertEquals("Electronics", fetchedCategory.getName());
        ((CategoryRepository)Mockito.verify(this.categoryRepository, Mockito.times(1))).findById(this.categoryId);
    }

    @Test
    public void testFindCategoryById_NotFound() {
        Mockito.when(this.categoryRepository.findById(this.categoryId)).thenReturn(Optional.empty());
        Assertions.assertThrows(CategoryNotFoundException.class, () -> this.categoryService.findCategoryById(this.categoryId));
        ((CategoryRepository)Mockito.verify(this.categoryRepository, Mockito.times(1))).findById(this.categoryId);
    }

    @Test
    public void testSaveCategory() {
        Mockito.when((Category)this.categoryRepository.save(this.category)).thenReturn(this.category);
        Category savedCategory = this.categoryService.saveCategory(this.category);
        Assertions.assertNotNull(savedCategory);
        Assertions.assertEquals("Electronics", savedCategory.getName());
        ((CategoryRepository)Mockito.verify(this.categoryRepository, Mockito.times(1))).save(this.category);
    }

    @Test
    public void testUpdateCategoryById() {
        Mockito.when(this.categoryRepository.findById(this.categoryId)).thenReturn(Optional.of(this.category));
        Mockito.when((Category)this.categoryRepository.save(this.category)).thenReturn(this.category);
        this.category.setName("Updated Electronics");
        Category updatedCategory = this.categoryService.updateCategoryById(this.categoryId, this.category);
        Assertions.assertNotNull(updatedCategory);
        Assertions.assertEquals("Updated Electronics", updatedCategory.getName());
        ((CategoryRepository)Mockito.verify(this.categoryRepository, Mockito.times(1))).findById(this.categoryId);
        ((CategoryRepository)Mockito.verify(this.categoryRepository, Mockito.times(1))).save(this.category);
    }

    @Test
    public void testUpdateCategoryById_NotFound() {
        Mockito.when(this.categoryRepository.findById(this.categoryId)).thenReturn(Optional.empty());
        Assertions.assertThrows(CategoryNotFoundException.class, () -> this.categoryService.updateCategoryById(this.categoryId, this.category));
        ((CategoryRepository)Mockito.verify(this.categoryRepository, Mockito.times(1))).findById(this.categoryId);
    }

    @Test
    public void testDeleteCategoryById() {
        Mockito.when(this.categoryRepository.findById(this.categoryId)).thenReturn(Optional.of(this.category));
        ((CategoryRepository)Mockito.doNothing().when(this.categoryRepository)).delete(this.category);
        this.categoryService.deleteCategoryById(this.categoryId);
        ((CategoryRepository)Mockito.verify(this.categoryRepository, Mockito.times(1))).findById(this.categoryId);
        ((CategoryRepository)Mockito.verify(this.categoryRepository, Mockito.times(1))).delete(this.category);
    }

    @Test
    public void testDeleteCategoryById_NotFound() {
        Mockito.when(this.categoryRepository.findById(this.categoryId)).thenReturn(Optional.empty());
        Assertions.assertThrows(CategoryNotFoundException.class, () -> this.categoryService.deleteCategoryById(this.categoryId));
        ((CategoryRepository)Mockito.verify(this.categoryRepository, Mockito.times(1))).findById(this.categoryId);
    }
}
