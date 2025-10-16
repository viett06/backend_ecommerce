package com.devteria.identity_service.mapper;

import com.devteria.identity_service.dto.request.UserRequest;
import com.devteria.identity_service.dto.request.UserUpdateRequest;
import com.devteria.identity_service.dto.response.UserResponse;
import com.devteria.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "customer", ignore = true)
    User toUser(UserRequest request);
    UserResponse toUserResponse(User user);
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "customer", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
