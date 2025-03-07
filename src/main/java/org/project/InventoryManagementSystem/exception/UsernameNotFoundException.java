package org.project.InventoryManagementSystem.exception;

import java.util.UUID;


public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String username, String message, UUID user_id) {
        super(message);
    }
}

