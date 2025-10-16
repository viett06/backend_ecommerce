//package com.devteria.identity_service.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(
//        name = "promotion_product"
//)
//public class PromotionProduct {
//    @EmbeddedId
//    private PromotionProductId promotionProductId;
//    @ManyToOne(
//            fetch = FetchType.LAZY
//    )
//    @MapsId(
//            "productId"
//    )
//    @JoinColumn(
//            name = "product_id",
//            referencedColumnName = "productId"
//    )
//    private Product product;
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
//}
