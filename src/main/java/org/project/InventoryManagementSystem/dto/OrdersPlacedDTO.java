package org.project.InventoryManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersPlacedDTO {
    private UUID orderId;
    private Date orderDate;
    private int quantity;
    private int totalPrice;
    private UUID customerId;
    private UUID productId;
    private UUID categoryId;
}
