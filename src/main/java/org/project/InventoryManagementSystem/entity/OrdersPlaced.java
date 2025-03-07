package org.project.InventoryManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Session;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.project.InventoryManagementSystem.dto.ProductDTO;
import org.project.InventoryManagementSystem.exception.ProductNotFoundException;
import org.project.InventoryManagementSystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ORDERSPLACED")
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
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
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
                    .orElseThrow(() -> new ProductNotFoundException("Product", "id", product_id));
            this.total_price = product.getPrice() * this.quantity;
        }
    }


}