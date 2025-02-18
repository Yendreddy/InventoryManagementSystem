package org.project.InventoryManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productId;
    private String name;
    private int price;
    private int quantity;
//    @ManyToOne(
//            cascade = CascadeType.ALL
//    )
//    private OrdersPlaced ordersPlaced;
//
//    @ManyToOne(mappedBy = "products")
//    private Category category;
}
