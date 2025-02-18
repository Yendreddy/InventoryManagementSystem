package org.project.InventoryManagementSystem.repository;

import org.project.InventoryManagementSystem.entity.OrdersPlaced;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrdersPlacedRepository extends JpaRepository<OrdersPlaced, UUID> {
}
