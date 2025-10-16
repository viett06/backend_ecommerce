package com.devteria.identity_service.service.impl;

import com.devteria.identity_service.constant.PredefinedRole;
import com.devteria.identity_service.dto.request.UserRequest;
import com.devteria.identity_service.dto.request.UserUpdateRequest;
import com.devteria.identity_service.dto.response.CartResponse;
import com.devteria.identity_service.dto.response.UserResponse;
import com.devteria.identity_service.entity.Cart;
import com.devteria.identity_service.entity.Customer;
import com.devteria.identity_service.entity.Role;
import com.devteria.identity_service.entity.User;
import com.devteria.identity_service.exception.AppException;
import com.devteria.identity_service.exception.ErrorCode;
import com.devteria.identity_service.mapper.UserMapper;
import com.devteria.identity_service.repository.CartRepository;
import com.devteria.identity_service.repository.CustomerRepository;
import com.devteria.identity_service.repository.RoleRepository;
import com.devteria.identity_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

import static com.devteria.identity_service.exception.ErrorCode.USERNAME_ALREADY_EXISTS;
import static com.devteria.identity_service.exception.ErrorCode.USER_NOT_EXISTED;
@Slf4j
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Transactional
    public UserResponse createUser(UserRequest request){
        User user = userMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassWord(passwordEncoder.encode(request.getPassWord()));
        HashSet<Role> roles = new HashSet<>();
        roleRepository.findById(PredefinedRole.USER_ROLE).ifPresent((role)->{roles.add(role);});
        user.setRoles(roles);
        try {
            user = userRepository.save(user);
        } catch (DataIntegrityViolationException exception) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        return userMapper.toUserResponse(user);
    }
    @PreAuthorize("hasAuthority('WRITE_USER')")
    public List<UserResponse> getUsers(){
        log.info("in method get users");
        List<User> user = userRepository.findAll();
        return user.stream().map(userMapper::toUserResponse).toList();
    }
    @PostAuthorize("returnObject.name == authentication.name")
    public UserResponse getUser(String id){
        User user = userRepository.findById(id).orElseThrow(()-> new AppException(USER_NOT_EXISTED));
        return userMapper.toUserResponse(user);
    }
    @Transactional
    @PreAuthorize("hasAuthority('DELETE_USER')")
    public void deleteUser(String id){
        userRepository.deleteById(id);
    }
    @PreAuthorize("hasAuthority('VIEW')")
    public UserResponse getMyInfor(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        User user = userRepository.findByName(name).orElseThrow(() -> new AppException(USER_NOT_EXISTED));
        return userMapper.toUserResponse(user);
    }
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse updateUser(String userId, UserUpdateRequest userUpdateRequest){
        User user = userRepository.findById(userId).orElseThrow(()-> new AppException(USER_NOT_EXISTED));
        if (userRepository.findByName(userUpdateRequest.getName()).isPresent()) {
            throw new AppException(USERNAME_ALREADY_EXISTS);
        }

        userMapper.updateUser(user,userUpdateRequest);
        user.setPassWord(passwordEncoder.encode(userUpdateRequest.getPassWord()));
var roles = roleRepository.findAllById(userUpdateRequest.getRoles());
user.setRoles(new HashSet<>(roles));
        return userMapper.toUserResponse(userRepository.save(user));
    }
}
