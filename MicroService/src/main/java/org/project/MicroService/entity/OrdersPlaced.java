package org.project.MicroService.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;

@Entity
@Data
@Table(
        name = "ORDERSPLACED"
)
public class OrdersPlaced implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.UUID
    )
    @Column(
            name = "order_id"
    )
    private UUID order_id;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(
            name = "order_date"
    )
    private Date order_date;
    private int quantity;
    private int total_price;
    @ManyToOne
    @JoinColumn(
            name = "customer_id",
            referencedColumnName = "customer_id"
    )
    private Customer customer;
    @Column(
            name = "product_id"
    )
    @JdbcTypeCode(12)
    private UUID product_id;
    @Column(
            name = "category_id"
    )
    @JdbcTypeCode(12)
    private UUID category_id;
}
