//package com.devteria.identity_service.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.math.BigDecimal;
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
//        name = "promotion"
//)
//public class Promotion {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long promotionId;
//    private String code;
//    private String description;
//    private String discountType;
//    private BigDecimal discountValue;
//    private LocalDateTime startDate;
//    private LocalDateTime endDate;
////    @OneToMany(
////            mappedBy = "promotion"
////    )
////    private Set<PromotionProduct> promotionProducts = new HashSet<>();
////    @OneToMany(
////            mappedBy = "promotion"
////    )
////    private Set<OrderPromotion> orderPromotions = new HashSet<>();
//}
