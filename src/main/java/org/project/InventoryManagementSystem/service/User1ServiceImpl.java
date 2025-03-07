package org.project.InventoryManagementSystem.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.project.InventoryManagementSystem.entity.User1;
import org.project.InventoryManagementSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class User1ServiceImpl implements User1Service {
    @Autowired
    private UserRepository userRepository;

    public User1ServiceImpl() {
    }

    public User1 saveUser(User1 user) {
        return userRepository.save(user);
    }

    public Optional<User1> getUserById(UUID user_id) {
        return Optional.ofNullable(userRepository.findById(user_id).orElseThrow(() -> new RuntimeException("User not found with id: " + user_id)));
    }

    public List<User1> getAllUsers() {
        return userRepository.findAll();
    }
}

