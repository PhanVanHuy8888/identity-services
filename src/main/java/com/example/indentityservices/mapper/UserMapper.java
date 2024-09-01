package com.example.indentityservices.mapper;

import com.example.indentityservices.dto.request.UserRequest;
import com.example.indentityservices.dto.request.UserUpdateRequest;
import com.example.indentityservices.dto.response.UserResponse;
import com.example.indentityservices.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequest request);

    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
