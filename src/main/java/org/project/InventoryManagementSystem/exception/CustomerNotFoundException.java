package org.project.InventoryManagementSystem.exception;

import java.util.UUID;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String message, String id, UUID customer_id) {
        super(message);
    }
}
