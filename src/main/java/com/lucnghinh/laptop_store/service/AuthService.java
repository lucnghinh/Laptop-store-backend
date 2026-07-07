package com.lucnghinh.laptop_store.service;

import com.lucnghinh.laptop_store.dto.request.LoginRequest;
import com.lucnghinh.laptop_store.dto.request.RegisterRequest;
import com.lucnghinh.laptop_store.dto.response.AuthResponse;
import com.lucnghinh.laptop_store.entity.Role;
import com.lucnghinh.laptop_store.entity.User;

import com.lucnghinh.laptop_store.exception.AuthenticationException;
import com.lucnghinh.laptop_store.exception.DuplicateResourceException;
import com.lucnghinh.laptop_store.exception.ErrorCode;

import com.lucnghinh.laptop_store.exception.ResourceNotFoundException;
import com.lucnghinh.laptop_store.mapper.UserMapper;
import com.lucnghinh.laptop_store.repository.RoleRepository;
import com.lucnghinh.laptop_store.repository.UserRepository;
import com.lucnghinh.laptop_store.security.CustomUserDetails;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {

    AuthenticationManager authenticationManager;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    JwtService jwtService;
    UserMapper userMapper;
    RoleRepository roleRepository;



    public AuthResponse register(RegisterRequest request) {
        if(userRepository.existsByUsername(request.getUsername())){
            throw new DuplicateResourceException(ErrorCode.USER_USERNAME_ALREADY_EXISTS);
        }
        if(userRepository.existsByEmail(request.getEmail())){
            throw new DuplicateResourceException(ErrorCode.USER_EMAIL_ALREADY_EXISTS);
        }

        User user = userMapper.toUser(request);
        Role role = roleRepository.findById("USER").orElseThrow(() -> new ResourceNotFoundException(ErrorCode.ROLE_NOT_FOUND));
        user.setRoles(new HashSet<>(List.of(role)));
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = userRepository.save(user);

        var token = jwtService.generateToken(savedUser);
        return AuthResponse.builder()
                .token(token)
                .build();
    }


    public AuthResponse login(LoginRequest request) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new AuthenticationException(ErrorCode.INVALID_CREDENTIALS);
        }


        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        User user = userDetails.getUser();

        String token = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(token)
                .build();
    }
}