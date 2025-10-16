package com.devteria.identity_service.controller;

import com.devteria.identity_service.dto.request.PermissionRequest;
import com.devteria.identity_service.dto.response.ApiResponse;
import com.devteria.identity_service.dto.response.PermissionResponse;
import com.devteria.identity_service.service.impl.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @PostMapping
    public ApiResponse<PermissionResponse> createPermission(@RequestBody PermissionRequest permissionRequest){
        PermissionResponse permission = permissionService.create(permissionRequest);
        return ApiResponse.<PermissionResponse>builder()
                .result(permission)
                .build();
    }
    @GetMapping
    public ApiResponse<List<PermissionResponse>> getPermission(){
        List<PermissionResponse> permission = permissionService.getPermissions();
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permission)
                .build();
    }
    @DeleteMapping("/{permission}")
    public ApiResponse<Void> deletePermission(@PathVariable String permission){
        permissionService.deletePermission(permission);
        return ApiResponse.<Void>builder().build();
    }

}
