package org.project.InventoryManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "ordersPlaced"
    )
    @JsonIgnore
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(name = "product_id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID product_id;

    @Column(name = "category_id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID category_id;

    public void calculateTotalPrice(Session session) {
        if (product_id != null) {
            Product product = session.get(Product.class, product_id);
            if (product != null) {
                total_price = product.getPrice() * quantity;
            }
        }
    }


}