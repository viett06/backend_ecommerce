package com.devteria.identity_service.controller;

import com.devteria.identity_service.dto.request.AuthenticationRequest;
import com.devteria.identity_service.dto.request.IntrospectRequest;
import com.devteria.identity_service.dto.request.LogoutRequest;
import com.devteria.identity_service.dto.request.RefreshTokenRequest;
import com.devteria.identity_service.dto.response.ApiResponse;
import com.devteria.identity_service.dto.response.AuthenticationResponse;
import com.devteria.identity_service.dto.response.IntrospectResponse;
import com.devteria.identity_service.service.impl.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/log-in")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        var response = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(response)
                .build();
    }
    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var response = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(response)
                .build();
    }
    @PostMapping("/logout")
    public ApiResponse<Void> logout(@RequestBody LogoutRequest logoutRequest) throws ParseException, JOSEException {
        authenticationService.logout(logoutRequest);
        return ApiResponse.<Void>builder()
                .build();
    }
    @PostMapping("/refresh")
    public ApiResponse<AuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest request)
            throws ParseException, JOSEException {
        var response = authenticationService.refreshToken(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(response)
                .build();
    }
}
