package com.devteria.identity_service.enums;


public enum PaymentStatus {
    PENDING,        // Đang chờ thanh toán
    PROCESSING,     // Đang xử lý
    COMPLETED,      // Thanh toán thành công
    FAILED,         // Thanh toán thất bại
    CANCELLED,      // Đã hủy
    REFUNDED        // Đã hoàn tiền
}
