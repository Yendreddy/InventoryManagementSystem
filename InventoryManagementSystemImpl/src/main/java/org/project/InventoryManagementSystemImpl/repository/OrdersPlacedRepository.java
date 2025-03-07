package org.project.InventoryManagementSystemImpl.repository;

import org.project.InventoryManagementSystemImpl.entity.OrdersPlaced;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrdersPlacedRepository extends JpaRepository<OrdersPlaced, UUID> {
}