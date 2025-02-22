package org.project.InventoryManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private UUID product_id;
    private String name;
    private int price;
    private int quantity;
    private UUID category_id;
}
