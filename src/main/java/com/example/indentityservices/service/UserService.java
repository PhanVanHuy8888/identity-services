package com.example.indentityservices.service;


import com.example.indentityservices.dto.request.UserRequest;
import com.example.indentityservices.dto.request.UserUpdateRequest;
import com.example.indentityservices.dto.response.ResultPagination;
import com.example.indentityservices.dto.response.UserResponse;
import com.example.indentityservices.exception.AppException;
import com.example.indentityservices.exception.ErrorCode;
import com.example.indentityservices.mapper.UserMapper;
import com.example.indentityservices.model.User;
import com.example.indentityservices.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{

    private final UserRepo userRepo;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

//    public UserDetailsService userDetail() {
//
//
//        return username -> userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Chuyển đổi User sang UserDetails với quyền
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole().getName()))
                        .collect(Collectors.toList())
        );
    }

    public UserResponse createUser(UserRequest request){
        if(userRepo.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.USER_EXISTED);
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());
        return userMapper.toUserResponse(userRepo.save(user));
    }

    public UserResponse updateUser(Long id, UserUpdateRequest request) {
        User u = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUser(u, request);
        return userMapper.toUserResponse(userRepo.save(u));
    }

    public ResultPagination getAll(Specification<User> spec, Pageable pageable) {
        Page<User> userPage = userRepo.findAll(spec, pageable);
        ResultPagination pagination = new ResultPagination();
        ResultPagination.Meta meta = new ResultPagination.Meta();

        meta.setPage(userPage.getNumber() + 1 );
        meta.setTotal(userPage.getTotalElements());
        meta.setPages(userPage.getTotalPages());
        meta.setPageSize(userPage.getSize());

        pagination.setMeta(meta);
        List<UserResponse> userResponses = userPage.getContent().stream().map(userMapper::toUserResponse).toList();

        pagination.setResult(userResponses);

        return pagination;
    }

    public UserResponse getUserById(Long id) {
        return userMapper.toUserResponse(userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found")));
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    public void deleteAllUser() {
        userRepo.deleteAll();
    }

}
