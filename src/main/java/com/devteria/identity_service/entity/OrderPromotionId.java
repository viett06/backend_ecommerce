//package com.devteria.identity_service.entity;
//
//import jakarta.persistence.Embeddable;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.Objects;
//
//@Embeddable
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//public class OrderPromotionId {
//    private Long orderId;
//    private Long promotionId;
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof OrderPromotionId that)) return false;
//        return Objects.equals(orderId, that.orderId) &&
//                Objects.equals(promotionId, that.promotionId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(orderId, promotionId);
//    }
//}
