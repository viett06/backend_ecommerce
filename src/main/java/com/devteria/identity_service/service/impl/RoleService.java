package com.devteria.identity_service.service.impl;

import com.devteria.identity_service.dto.request.RoleRequest;
import com.devteria.identity_service.dto.response.RoleResponse;
import com.devteria.identity_service.mapper.RoleMapper;
import com.devteria.identity_service.repository.PermissionRepository;
import com.devteria.identity_service.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionRepository permissionRepository;
    @PreAuthorize("hasRole('ADMIN')")
    public RoleResponse create(RoleRequest roleRequest){
    var role = roleMapper.toRole(roleRequest);
    var permissions = permissionRepository.findAllById(roleRequest.getPermissions());
    role.setPermissions(new HashSet<>(permissions));
    role=roleRepository.save(role);
    return roleMapper.toRoleResponse(role);
}
    @PreAuthorize("hasRole('ADMIN')")
    public List<RoleResponse> getRole(){
    var roles = roleRepository.findAll();
    return roles.stream().map(role -> roleMapper.toRoleResponse(role)).toList();
}
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteRole(String role){
    roleRepository.deleteById(role);
}
}
