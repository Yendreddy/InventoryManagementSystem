package org.project.InventoryManagementSystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.project.InventoryManagementSystem.entity.User1;
import org.project.InventoryManagementSystem.service.User1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "User management APIs")
public class User1Controller {

    @Autowired
    private User1Service user1Service;

    @PostMapping("/save")
    @Operation(summary = "Save a new user")
    public ResponseEntity<User1> saveUser(@RequestBody User1 user) {
        User1 savedUser = user1Service.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
}
