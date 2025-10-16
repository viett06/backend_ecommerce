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
    INVENTORY_PROFILE_NOT_EXISTED(1016, "inventory profile not existed", HttpStatus.NOT_FOUND)

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
