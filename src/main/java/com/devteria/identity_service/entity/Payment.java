//package com.devteria.identity_service.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@Table(
//        name = "payment"
//)
//public class Payment {
//    @Id
//    @SequenceGenerator(
//            name = "payment_sequence",
//            sequenceName = "payment_sequence",
//            allocationSize = 1
//
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "payment_sequence"
//    )
//    private Long paymentId;
//    private BigDecimal amount;
//    private LocalDateTime paymentDate;
//    private String paymentMethod;
//    private String status;
//    @OneToMany(
//            mappedBy = "payment"
//    )
//    private Set<Order> orders = new HashSet<>();
//
//}
