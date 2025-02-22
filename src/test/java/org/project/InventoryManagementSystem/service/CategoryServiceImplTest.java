package org.project.InventoryManagementSystem.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project.InventoryManagementSystem.dto.CategoryDTO;
import org.project.InventoryManagementSystem.entity.Category;
import org.project.InventoryManagementSystem.exception.CategoryNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Transaction transaction;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Category category;

    @BeforeEach
    public void setUp() {
        category = Category.builder()
                .category_id(UUID.randomUUID())
                .name("Electronics")
                .build();

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
    }

    @Test
    public void testGetAllCategories() {
        when(session.createQuery("FROM Category", Category.class).list()).thenReturn(Collections.singletonList(category));

        List<CategoryDTO> categories = categoryService.fetchCategoryList();

        assertNotNull(categories);
        assertEquals(1, categories.size());
        verify(session, times(1)).createQuery("FROM Category", Category.class);
    }

    @Test
    public void testSaveCategory() {
        boolean isSaved = categoryService.saveCategory(CategoryDTO.builder().build());

        assertTrue(isSaved);
        verify(session, times(1)).save(any(Category.class));
    }

    @Test
    public void testUpdateCategoryById() {
        when(session.get(Category.class, category.getCategory_id())).thenReturn(category);

        boolean isUpdated = categoryService.updateCategoryById(category.getCategory_id(), CategoryDTO.builder().build());

        assertTrue(isUpdated);
        verify(session, times(1)).merge(any(Category.class));
    }

    @Test
    public void testDeleteCategoryById() {
        when(session.get(Category.class, category.getCategory_id())).thenReturn(category);

        boolean isDeleted = categoryService.deleteCategoryById(category.getCategory_id());

        assertTrue(isDeleted);
        verify(session, times(1)).delete(category);
    }

    @Test
    public void testGetCategoryById() {
        when(session.get(Category.class, category.getCategory_id())).thenReturn(category);

        CategoryDTO foundCategory = categoryService.findCategoryById(category.getCategory_id());

        assertNotNull(foundCategory);
        verify(session, times(1)).get(Category.class, category.getCategory_id());
    }

    @Test
    public void testCategoryNotFoundException() {
        when(session.get(Category.class, category.getCategory_id())).thenReturn(null);

        assertThrows(CategoryNotFoundException.class, () -> categoryService.findCategoryById(category.getCategory_id()));
    }
}