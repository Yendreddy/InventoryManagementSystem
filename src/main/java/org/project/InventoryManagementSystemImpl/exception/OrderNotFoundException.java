package org.project.InventoryManagementSystemImpl.exception;

import java.util.UUID;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(UUID message) {
        super(String.valueOf(message));
    }
}
