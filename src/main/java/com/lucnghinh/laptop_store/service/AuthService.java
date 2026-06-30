package com.lucnghinh.laptop_store.service;

import com.lucnghinh.laptop_store.dto.request.LoginRequest;
import com.lucnghinh.laptop_store.dto.request.RegisterRequest;
import com.lucnghinh.laptop_store.dto.response.AuthResponse;
import com.lucnghinh.laptop_store.entity.User;

import com.lucnghinh.laptop_store.exception.AuthenticationException;
import com.lucnghinh.laptop_store.exception.DuplicateResourceException;
import com.lucnghinh.laptop_store.exception.ErrorCode;

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


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {

    AuthenticationManager authenticationManager;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    JwtService jwtService;


    public AuthResponse register(RegisterRequest request) {
        if(userRepository.existsByUsername(request.getUsername())){
            throw new DuplicateResourceException(ErrorCode.USER_USERNAME_ALREADY_EXISTS);
        }
        if(userRepository.existsByEmail(request.getEmail())){
            throw new DuplicateResourceException(ErrorCode.USER_EMAIL_ALREADY_EXISTS);
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());
        User savedUser = userRepository.save(user);

        var token = jwtService.generateToken(savedUser);
        return AuthResponse.builder()
                .token(token)
                .build();
    }


    public AuthResponse login(LoginRequest request) {
//        User user = userRepository.findByUsername(request.getUsername())
//                .orElseThrow(() -> new AuthenticationException(ErrorCode.INVALID_CREDENTIALS));
//
//        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
//            throw new AuthenticationException(ErrorCode.INVALID_CREDENTIALS);
//        }
//        return  AuthResponse.builder()
//                .token(jwtService.generateToken(user))
//                .build();
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