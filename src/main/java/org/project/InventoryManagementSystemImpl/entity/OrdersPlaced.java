package org.project.InventoryManagementSystemImpl.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.project.InventoryManagementSystemImpl.exception.ProductNotFoundException;
import org.project.InventoryManagementSystemImpl.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "ORDERSPLACED")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersPlaced implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_id")
    private UUID order_id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date")
    private Date order_date;
    private int quantity;
    private int total_price;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "product_id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID product_id;

    @Column(name = "category_id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID category_id;

    @Transient
    @Autowired
    private ProductRepository productRepository;

    public void calculateTotalPrice() {
        if (this.product_id != null) {
            Product product = productRepository.findById(this.product_id)
                    .orElseThrow(() -> new ProductNotFoundException( this.product_id));
            this.total_price = product.getPrice() * this.quantity;
        }

    }
}
