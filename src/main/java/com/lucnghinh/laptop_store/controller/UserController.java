package com.lucnghinh.laptop_store.controller;

import com.lucnghinh.laptop_store.dto.request.UpdateRoleRequest;
import com.lucnghinh.laptop_store.dto.response.ApiResponse;
import com.lucnghinh.laptop_store.dto.response.UserResponse;
import com.lucnghinh.laptop_store.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{username}/roles")
    public ApiResponse<UserResponse> updateUserRoles(@PathVariable String username,@RequestBody UpdateRoleRequest updateRoleRequest){
        return ApiResponse.<UserResponse>builder()
                .data(userService.updateUserRoles(username,updateRoleRequest.getRoles()))
                .build();
    }
}
