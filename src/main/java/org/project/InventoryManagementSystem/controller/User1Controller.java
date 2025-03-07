package org.project.InventoryManagementSystem.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.project.InventoryManagementSystem.entity.User1;
import org.project.InventoryManagementSystem.service.User1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/users"})
public class User1Controller {
    @Autowired
    private User1Service user1Service;

    public User1Controller() {
    }

    @PostMapping({"/save"})
    public ResponseEntity<User1> saveUser(@RequestBody User1 user) {
        User1 savedUser = this.user1Service.saveUser(user);
        return new ResponseEntity(savedUser, HttpStatus.CREATED);
    }
}
