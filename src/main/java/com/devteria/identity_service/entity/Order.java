package com.devteria.identity_service.entity;

import com.devteria.identity_service.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "orders"
)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @CreationTimestamp
    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderStatus status;
    @Column(length = 500)
    private String note;
    @Column(name = "total_amount", precision = 10, scale = 2)
    private BigDecimal totalAmount;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "customer_id",
            referencedColumnName = "customerId"
    )
    private Customer customer;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "payment_id",
            referencedColumnName = "paymentId"
    )
    private Payment payment;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "shipment_id",
            referencedColumnName = "shipmentId"
    )
    private Shipment shipment;
    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<OrderItem> orderItems = new HashSet<>();
//    @OneToMany(
//            mappedBy = "order"
//    )
//    private Set<OrderPromotion> orderPromotions = new HashSet<>();

}
