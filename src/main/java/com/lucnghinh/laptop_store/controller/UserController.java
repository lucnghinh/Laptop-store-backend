package com.lucnghinh.laptop_store.controller;

import com.lucnghinh.laptop_store.dto.response.ApiResponse;
import com.lucnghinh.laptop_store.dto.response.UserResponse;
import com.lucnghinh.laptop_store.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;

    @GetMapping("/getInfo")
    public ApiResponse<UserResponse> getInfo(){
        return ApiResponse.<UserResponse>builder()
                .data(userService.getMyInfo())
                .build();
    }
}
