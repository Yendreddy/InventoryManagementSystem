package org.project.InventoryManagementSystemImpl.exception;

import java.util.UUID;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(UUID message) {
        super(String.valueOf(message));
    }
}
