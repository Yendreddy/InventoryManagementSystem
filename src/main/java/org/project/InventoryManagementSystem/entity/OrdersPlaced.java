package org.project.InventoryManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersPlaced {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderId;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date")
    private Date orderDate;
    private Product price;
    private Product name;
    private int quantity;
    private int totalPrice;

//    @ManyToOne(
//            cascade = CascadeType.ALL
//    )
//    @JoinColumn(
//            name = "customer_id",
//            referencedColumnName = "customerId"
//    )
//    private Customer customer;
//
//    @OneToMany(
//            cascade = CascadeType.ALL,
//            mappedBy = "ordersPlaced"
//    )
//    private List<Product> products;
}

