package com.devteria.identity_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public enum ErrorCode {
    //Trong Java, enum thực chất là một loại class đặc biệt, và mỗi “giá trị” của enum (ví dụ USER_NOT_EXISTED) là một instance (đối tượng) được tạo ra từ enum đó.
    USER_NOT_EXISTED(1005, "User not existed", HttpStatus.NOT_FOUND),
    PERMISSION_NOT_EXISTED(1007, "you not authorization", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    USERNAME_ALREADY_EXISTS(1001, "Tên đã tồn tại", HttpStatus.CONFLICT),
    INVALID_DOB(1002, "Invalid date of birth {min}", HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1003, "Uncategorized error", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1004, "User existed", HttpStatus.BAD_REQUEST),
    CUSTOMER_NOT_EXISTED(1008, "Customer not existed", HttpStatus.NOT_FOUND),
    ADDRESS_NOT_EXISTED(1009, "Address not existed", HttpStatus.NOT_FOUND),
    CUSTOMER_EXISTED(1011,"Customer existed",HttpStatus.BAD_REQUEST),
    CART_NOT_EXISTED(1012, "Cart not existed", HttpStatus.NOT_FOUND),
    PRODUCT_NOT_EXISTED(1013, "Product not existed", HttpStatus.NOT_FOUND),
    CART_ITEM_NOT_EXISTED(1014, "cart item not existed", HttpStatus.NOT_FOUND),
    CATEGORY_NOT_EXISTED(1015, "category not existed", HttpStatus.NOT_FOUND),
    INVENTORY_PROFILE_NOT_EXISTED(1016, "inventory profile not existed", HttpStatus.NOT_FOUND),
    ORDER_NOT_FOUND(1017, "Order not existed", HttpStatus.NOT_FOUND),
    CUSTOMER_NOT_FOUND(1018, "Customer not existed", HttpStatus.NOT_FOUND),
    ORDER_ALREADY_DELIVERED(1019, "Order has already been delivered and cannot be cancelled", HttpStatus.BAD_REQUEST),
    ORDER_ITEM_NOT_FOUND(1020, "OrderItem not existed", HttpStatus.NOT_FOUND),
    ORDER_ITEM_ALREADY_EXISTS(1021, "Order item already exists in this order", HttpStatus.BAD_REQUEST),
    SHIPMENT_NOT_FOUND(1022, "Shipment not found", HttpStatus.NOT_FOUND),
    SHIPMENT_TRACKING_NUMBER_EXISTS(1023, "Tracking number already exists", HttpStatus.BAD_REQUEST),
    SHIPMENT_HAS_ORDERS(1024, "Cannot delete shipment with associated orders", HttpStatus.BAD_REQUEST),
    INVALID_DATE_RANGE(1025, "Start date must be before end date", HttpStatus.BAD_REQUEST),
    PAYMENT_NOT_FOUND(1026, "Payment not found", HttpStatus.NOT_FOUND),
    PAYMENT_HAS_ORDERS(1027, "Cannot delete payment with associated orders", HttpStatus.BAD_REQUEST),
    PAYMENT_GATEWAY_ERROR(1028, "Payment gateway error", HttpStatus.INTERNAL_SERVER_ERROR),
    PAYMENT_SIGNATURE_ERROR(1029, "Payment signature error", HttpStatus.INTERNAL_SERVER_ERROR),
    PAYMENT_SIGNATURE_INVALID(1030, "Invalid payment signature", HttpStatus.BAD_REQUEST),
    PAYMENT_PROCESSING_ERROR(1031, "Payment processing error", HttpStatus.INTERNAL_SERVER_ERROR),
    INSUFFICIENT_BALANCE(1032, "Insufficient balance", HttpStatus.BAD_REQUEST);





    ;
    private int code;
    private String message;
    private HttpStatusCode statusCode;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatusCode getStatusCode() {
        return statusCode;
    }

    private ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
