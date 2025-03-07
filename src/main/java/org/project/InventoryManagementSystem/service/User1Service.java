package org.project.InventoryManagementSystem.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.project.InventoryManagementSystem.entity.User1;

public interface User1Service {
    User1 saveUser(User1 user);

    Optional<User1> getUserById(UUID user_id);

    List<User1> getAllUsers();
}
