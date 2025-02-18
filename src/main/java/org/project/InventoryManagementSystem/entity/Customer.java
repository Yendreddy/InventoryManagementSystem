package org.project.InventoryManagementSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID customerId;
    private String name;
    private String phoneNumber;
    @AttributeOverride(
            name = "emailId",
            column = @Column(name = "customer_email_address")
    )
    private String emailId;

//    @OneToMany(mappedBy = "customer")
//    private List<OrdersPlaced> ordersPlaceds;
}
