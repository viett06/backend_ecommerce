//package com.devteria.identity_service.entity;
//
//import jakarta.persistence.Embeddable;
//import lombok.*;
//
//import java.io.Serializable;
//import java.util.Objects;
//
//@Embeddable
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//public class PromotionProductId implements Serializable {
//    private Long promotionId;
//    private Long productId;
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof PromotionProductId that)) return false;
//        return Objects.equals(productId, that.productId) &&
//                Objects.equals(promotionId, that.promotionId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(productId, promotionId);
//    }
//}
