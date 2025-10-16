//package com.devteria.identity_service.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//import org.hibernate.annotations.CreationTimestamp;
//
//import java.time.LocalDateTime;
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(
//        name = "orders"
//)
//public class Order {
//    @Id
//    @SequenceGenerator(
//            name = "order_sequence",
//            sequenceName = "order_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "order_sequence"
//    )
//    private Long orderId;
//    @CreationTimestamp
//    private LocalDateTime orderDate;
//    private String status;
//    @ManyToOne(
//            fetch = FetchType.LAZY
//    )
//    @JoinColumn(
//            name = "customer_id",
//            referencedColumnName = "customerId"
//    )
//    private Customer customer;
//    @ManyToOne(
//            fetch = FetchType.LAZY
//    )
//    @JoinColumn(
//            name = "payment_id",
//            referencedColumnName = "paymentId"
//    )
//    private Payment payment;
//    @ManyToOne(
//            fetch = FetchType.LAZY
//    )
//    @JoinColumn(
//            name = "shipment_id",
//            referencedColumnName = "shipmentId"
//    )
//    private Shipment shipment;
//    @OneToMany(
//            mappedBy = "order",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private Set<OrderItem> orderItems = new HashSet<>();
//    @OneToMany(
//            mappedBy = "order"
//    )
//    private Set<OrderPromotion> orderPromotions = new HashSet<>();
//
//}
