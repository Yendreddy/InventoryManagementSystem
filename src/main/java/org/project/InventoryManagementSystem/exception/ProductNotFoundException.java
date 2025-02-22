package org.project.InventoryManagementSystem.exception;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String product, String message, UUID product_id) {
        super(message);
    }
}
