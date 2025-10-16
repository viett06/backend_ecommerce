package com.devteria.identity_service.service.impl;

import com.devteria.identity_service.dto.request.PermissionRequest;
import com.devteria.identity_service.dto.response.PermissionResponse;
import com.devteria.identity_service.entity.Permission;
import com.devteria.identity_service.mapper.PermissionMapper;
import com.devteria.identity_service.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private PermissionMapper permissionMapper;
    @PreAuthorize("hasRole('ADMIN')")
    public PermissionResponse create(PermissionRequest request){
        Permission permission = permissionRepository.save(permissionMapper.toPermission(request));
        return permissionMapper.toPermissionResponse(permission);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<PermissionResponse> getPermissions(){
        List<Permission> permissions = permissionRepository.findAll();
        return permissions.stream().map(permission -> permissionMapper.toPermissionResponse(permission)).collect(Collectors.toList());
    }
    @PreAuthorize("hasRole('ADMIN')")
    public void deletePermission(String permission){
        permissionRepository.deleteById(permission);
    }
}
