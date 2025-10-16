//package com.devteria.identity_service.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//import org.hibernate.annotations.CreationTimestamp;
//
//import java.time.LocalDateTime;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@Table(
//        name = "review"
//)
//public class Review {
//    @Id
//    @SequenceGenerator(
//            name = "review_sequence",
//            sequenceName = "review_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "review_sequence"
//    )
//    private Long reviewId;
//    private int rating;
//    private String comment;
//    @CreationTimestamp
//    private LocalDateTime createAt;
//    @ManyToOne
//    @JoinColumn(
//            name = "customerId",
//            referencedColumnName = "customerId"
//    )
//    private Customer customer;
////    @ManyToOne
////    @JoinColumn(
////            name = "product_id",
////            referencedColumnName = "productId"
////
////    )
////    private Product product;
//    @OneToOne(
//            optional = false,
//            fetch = FetchType.LAZY
//    )
//    @JoinColumns({
//            @JoinColumn(name = "order_id", referencedColumnName = "orderId"),
//            @JoinColumn(name = "product_id", referencedColumnName = "productId")
//    })
//    private OrderItem orderItem;
//}
