package com.devteria.identity_service.exception;

import com.devteria.identity_service.dto.response.ApiResponse;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private static final String MIN_ATTRIBUTE = "min";
//    @ExceptionHandler(value = Exception.class)
    //ResponseEntity<> tra ve httpstatus va mot body tu dinh nghia
//    ResponseEntity<Map<String, Object>> handlingRuntimeException(RuntimeException runtimeException){
//Map<String, Object> body = new LinkedHashMap<>();
//        body.put("message",runtimeException.getMessage());
//        body.put("status", HttpStatus.NOT_FOUND.value());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
//    }

    /*@ExceptionHandler(value = AppException.class)
    public ApiResponse<ResponseEntity<Map<String,Object>>> handlingAppException(AppException appException){
        Map<String,Object> body = new LinkedHashMap<>();
        body.put("message",appException.getErorrCode().getMessage());
        body.put("code", appException.getErorrCode().getCode());
        return ApiResponse.<ResponseEntity<Map<String,Object>>>builder()
                .result(ResponseEntity.status(appException.getErorrCode().getStatusCode()).body(body))
                .build();
    }*/
    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ApiResponse<Object>> handlingAppException(AppException appException){
        ErrorCode errorCode = appException.getErorrCode();
        ApiResponse<Object> response = ApiResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
        return ResponseEntity.status(errorCode.getStatusCode()).body(response);
    }
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception) {
        log.error("Exception: ", exception);
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException exception) {
        String enumKey = exception.getFieldError().getDefaultMessage();

        ErrorCode errorCode = ErrorCode.INVALID_KEY;
        Map<String, Object> attributes = null;
        try {
            errorCode = ErrorCode.valueOf(enumKey);

            var constraintViolation =
                    exception.getBindingResult().getAllErrors().get(0).unwrap(ConstraintViolation.class);

            attributes = constraintViolation.getConstraintDescriptor().getAttributes();

            log.info(attributes.toString());

        } catch (IllegalArgumentException e) {

        }

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(
                Objects.nonNull(attributes)
                        ? mapAttribute(errorCode.getMessage(), attributes)
                        : errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }
    private String mapAttribute(String message, Map<String, Object> attributes) {
        String minValue = String.valueOf(attributes.get(MIN_ATTRIBUTE));

        return message.replace("{" + MIN_ATTRIBUTE + "}", minValue);
    }

}
