package com.lucnghinh.laptop_store.controller;

import com.lucnghinh.laptop_store.dto.request.LoginRequest;
import com.lucnghinh.laptop_store.dto.request.RegisterRequest;
import com.lucnghinh.laptop_store.dto.response.ApiResponse;
import com.lucnghinh.laptop_store.dto.response.AuthResponse;
import com.lucnghinh.laptop_store.service.AuthService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/auth")
public class AuthController {
    AuthService authService;

    @PostMapping("/register")
    public ApiResponse<AuthResponse> register(@RequestBody @Valid RegisterRequest request) {
        var data = authService.register(request);
        return ApiResponse.<AuthResponse>builder()
                .data(data)
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@RequestBody @Valid LoginRequest request) {
        var data = authService.login(request);
        return ApiResponse.<AuthResponse>builder()
                .data(data)
                .build();
    }
}
