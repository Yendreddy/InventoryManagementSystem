package org.project.InventoryManagementSystemImpl.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.project.InventoryManagementSystemImpl.exception.ProductNotFoundException;
import org.project.InventoryManagementSystemImpl.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

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
    @Transient
    @Autowired
    private ProductRepository productRepository;

    public void calculateTotalPrice() {
        if (this.product_id != null) {
            Product product = productRepository.findById(this.product_id)
                    .orElseThrow(() -> new ProductNotFoundException("Product not found" + this.product_id));
            this.total_price = product.getPrice() * this.quantity;
        }

    }
}