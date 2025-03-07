package org.project.InventoryManagementSystem.repository;

import java.util.Optional;
import java.util.UUID;
import org.project.InventoryManagementSystem.entity.User1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User1, UUID> {
    Optional<User1> findByUsername(String email);
}

