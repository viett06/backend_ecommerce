package com.devteria.identity_service.controller;

import com.devteria.identity_service.dto.request.UserRequest;
import com.devteria.identity_service.dto.request.UserUpdateRequest;
import com.devteria.identity_service.dto.response.ApiResponse;
import com.devteria.identity_service.dto.response.UserResponse;
import com.devteria.identity_service.service.impl.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public ApiResponse<UserResponse> createUser(@Valid @RequestBody UserRequest request){
        UserResponse result= userService.createUser(request);
        return ApiResponse.<UserResponse>builder()
                .result(result)
                .build();
    }
    @GetMapping("/{userId}")
    public ApiResponse<UserResponse> getUser(@PathVariable("userId") String id){

        UserResponse userResponse = userService.getUser(id);
        return ApiResponse.<UserResponse>builder()
                .result(userResponse)
                .build();
    }
    @GetMapping("/myinfor")
    public ApiResponse<UserResponse> getMyInfor(){

        UserResponse userResponse = userService.getMyInfor();
        return ApiResponse.<UserResponse>builder()
                .result(userResponse)
                .build();
    }
    @GetMapping
    public ApiResponse<List<UserResponse>> getUsers(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: {}",authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        List<UserResponse> userResponses = userService.getUsers();
        return ApiResponse.<List<UserResponse>>builder()
                .result(userResponses)
                .build();
    }
    @DeleteMapping("/{userId}")
    public ApiResponse<Object> deleteUser(@PathVariable("userId") String id){
        userService.deleteUser(id);
        return ApiResponse.<Object>builder()
                .build();
    }

    @PutMapping("/{userId}")
    public ApiResponse<UserResponse> updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest request) {
        UserResponse userResponse = userService.updateUser(userId, request);
        return ApiResponse.<UserResponse>builder()
                .result(userResponse)
                .build();
    }

}
