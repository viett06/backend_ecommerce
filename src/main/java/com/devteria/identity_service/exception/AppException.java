package com.devteria.identity_service.exception;

public class AppException extends RuntimeException{
    private ErrorCode erorrCode;

    public ErrorCode getErorrCode() {
        return erorrCode;
    }

    public void setErorrCode(ErrorCode erorrCode) {
        this.erorrCode = erorrCode;
    }

    public AppException(ErrorCode erorrCode) {
        super(erorrCode.getMessage());
        this.erorrCode = erorrCode;
    }
}
