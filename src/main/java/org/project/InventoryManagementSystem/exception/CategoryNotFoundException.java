package org.project.InventoryManagementSystem.exception;

import java.util.UUID;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(String category, String message, UUID category_id) {
        super(message);
    }
}
