package com.devteria.identity_service.entity;

import com.devteria.identity_service.enums.ShipmentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(
        name = "shipment"
)
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipmentId;
    @Column(name = "shipped_date")
    private LocalDate shippedDate;
    @NotBlank
    @Size(max = 100)
    @Column(name = "carrier", nullable = false, length = 100)
    private String carrier;
    @NotBlank
    @Size(max = 50)
    @Column(name = "tracking_number", nullable = false, unique = true, length = 50)
    private String trackingNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private ShipmentStatus status;
    @OneToMany(
            mappedBy = "shipment"
    )
    private Set<Order> orders = new HashSet<>();
}
