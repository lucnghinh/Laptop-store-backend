package com.lucnghinh.laptop_store.service;

import com.lucnghinh.laptop_store.dto.request.*;
import com.lucnghinh.laptop_store.dto.response.AuthResponse;
import com.lucnghinh.laptop_store.dto.response.IntrospectResponse;
import com.lucnghinh.laptop_store.entity.InvalidatedToken;
import com.lucnghinh.laptop_store.entity.Role;
import com.lucnghinh.laptop_store.entity.User;

import com.lucnghinh.laptop_store.exception.AuthenticationException;
import com.lucnghinh.laptop_store.exception.DuplicateResourceException;
import com.lucnghinh.laptop_store.exception.ErrorCode;

import com.lucnghinh.laptop_store.exception.ResourceNotFoundException;
import com.lucnghinh.laptop_store.mapper.UserMapper;
import com.lucnghinh.laptop_store.repository.InvalidatedTokenRepository;
import com.lucnghinh.laptop_store.repository.RoleRepository;
import com.lucnghinh.laptop_store.repository.UserRepository;
import com.lucnghinh.laptop_store.security.CustomUserDetails;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
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
    InvalidatedTokenRepository invalidatedTokenRepository;


    public IntrospectResponse introspect(IntrospectRequest request) {
        boolean isValid = true;

        try {
            jwtService.verifyToken(request.getToken());
        } catch (AuthenticationException e) {
            isValid = false;
        }
        return  IntrospectResponse.builder()
                .valid(isValid)
                .build();
    }


    public AuthResponse register(RegisterRequest request) throws JOSEException {
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
        userRepository.save(user);

        return  AuthResponse.builder()
                .accessToken(jwtService.generateAccessToken(user))
                .refreshToken(jwtService.generateRefreshToken(user))
                .build();
    }


    public AuthResponse login(LoginRequest request) throws JOSEException {
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

        return  AuthResponse.builder()
                .accessToken(jwtService.generateAccessToken(user))
                .refreshToken(jwtService.generateRefreshToken(user))
                .build();
    }


    public void logout(LogoutRequest request) throws ParseException {
        SignedJWT signedJWT = jwtService.verifyToken(request.getToken());

        String jit = signedJWT.getJWTClaimsSet().getJWTID();
        Date exp = signedJWT.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jit)
                .expiryDate(exp)
                .build();
        invalidatedTokenRepository.save(invalidatedToken);
    }

    public AuthResponse refresh(RefreshRequest request) throws ParseException, JOSEException {
        SignedJWT signedJWT = jwtService.verifyRefreshToken(request.getRefreshToken());

        String username = signedJWT.getJWTClaimsSet().getSubject();

        User user = userRepository.findByUsername(username).orElseThrow(() -> new AuthenticationException(ErrorCode.UNAUTHENTICATED));

        String access = jwtService.generateAccessToken(user);

        return AuthResponse.builder()
                .accessToken(access)
                .refreshToken(request.getRefreshToken())
                .build();
    }
}