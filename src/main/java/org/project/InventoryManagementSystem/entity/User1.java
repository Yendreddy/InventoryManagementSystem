package org.project.InventoryManagementSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;

import lombok.Data;
import lombok.Generated;
import org.project.InventoryManagementSystem.enums.Role;

@Entity
@Data
public class User1 {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID user_id;
    private String username;
    private String password;
    private Role role;
}