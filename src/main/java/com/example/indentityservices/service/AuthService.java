package com.example.indentityservices.service;


import com.example.indentityservices.dto.request.LoginRequest;
import com.example.indentityservices.dto.response.TokenResponse;
import com.example.indentityservices.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;


    public TokenResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userRepo.findByEmail(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Username or password incorrect"));
        String accessToken = jwtService.generateToken(user);

        String refreshToken = jwtService.generateRefreshToken(user);


        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.getId())
                .build();
    }
}
