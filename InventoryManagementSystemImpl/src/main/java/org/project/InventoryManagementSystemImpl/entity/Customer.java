package org.project.InventoryManagementSystemImpl.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    private UUID customer_id;

    @NotBlank(message = "Name should not be blank")
    private String name;

    @Pattern(regexp = "\\d{10}", message = "Phone number should be 10 digits")
    private String phone_number;

    @Email(message = "Email should be valid")
    private String email_id;

    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER

    )
    @JsonIgnore
    private List<OrdersPlaced> ordersPlaceds;
}
