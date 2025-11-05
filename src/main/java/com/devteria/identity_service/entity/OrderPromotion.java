//package com.devteria.identity_service.entity;
//
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.math.BigDecimal;
//
//import java.time.LocalDateTime;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@Table(
//        name = "order_promotion"
//)
//public class OrderPromotion {
//    @EmbeddedId
//    private OrderPromotionId orderPromotionId;
//    @ManyToOne(
//            fetch = FetchType.LAZY
//    )
//    @MapsId(
//            "orderId"
//    )
//    @JoinColumn(
//            name = "order_id",
//            referencedColumnName = "orderId"
//    )
//    private Order order;
//    @ManyToOne(
//            fetch = FetchType.LAZY
//    )
//    @MapsId(
//            "promotionId"
//    )
//    @JoinColumn(
//            name = "promotion_id",
//            referencedColumnName = "promotionId"
//    )
//    private Promotion promotion;
//    private BigDecimal discountValue;
//    private LocalDateTime appliedDate;
//
//}
