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
    private UUID order_id;
    private Date order_date;
    private int quantity;
    private int total_price;
    private UUID customer_id;
    private UUID product_id;
    private UUID category_id;
}
