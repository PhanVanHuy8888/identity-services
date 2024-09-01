package com.example.indentityservices.service;


import com.example.indentityservices.dto.request.UserRequest;
import com.example.indentityservices.dto.request.UserUpdateRequest;
import com.example.indentityservices.dto.response.UserResponse;
import com.example.indentityservices.exception.AppException;
import com.example.indentityservices.exception.ErrorCode;
import com.example.indentityservices.mapper.UserMapper;
import com.example.indentityservices.model.User;
import com.example.indentityservices.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserRequest request){
        if(userRepo.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.USER_EXISTED);
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toUserResponse(userRepo.save(user));
    }

    public UserResponse updateUser(Long id, UserUpdateRequest request) {
        User u = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUser(u, request);
        return userMapper.toUserResponse(userRepo.save(u));
    }

    public List<UserResponse> getAll() {
        return userRepo.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    public UserResponse getUserById(Long id) {
        return userMapper.toUserResponse(userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found")));
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }


}
