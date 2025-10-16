//package com.devteria.identity_service.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.time.LocalDate;
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@Table(
//        name = "shipment"
//)
//public class Shipment {
//    @Id
//    @SequenceGenerator(
//            name = "shipment_sequence",
//            sequenceName = "shipment_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "shipment_sequence"
//    )
//    private Long shipmentId;
//    private LocalDate shippedDate;
//    private String carrier;
//    private String trackingNumber;
//    private String status;
//    @OneToMany(
//            mappedBy = "shipment"
//    )
//    private Set<Order> orders = new HashSet<>();
//}
