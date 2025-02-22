package org.project.InventoryManagementSystem.exception;

import java.util.UUID;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String order, String message, UUID order_id) {
        super(message);
    }
}
