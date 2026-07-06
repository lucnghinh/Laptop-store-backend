package com.lucnghinh.laptop_store.controller;

import com.lucnghinh.laptop_store.dto.request.RoleRequest;
import com.lucnghinh.laptop_store.dto.response.ApiResponse;
import com.lucnghinh.laptop_store.dto.response.RoleResponse;
import com.lucnghinh.laptop_store.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/role")
public class RoleController {
    RoleService roleService;

    @PostMapping("/create")
    public ApiResponse<RoleResponse> create(@RequestBody RoleRequest roleRequest) {
        return ApiResponse.<RoleResponse>builder()
                .data(roleService.create(roleRequest))
                .build();
    }

    @GetMapping("/getAll")
    public ApiResponse<List<RoleResponse>> getAll() {
        return ApiResponse.<List<RoleResponse>>builder()
                .data(roleService.getAll())
                .build();
    }

    @DeleteMapping("/{role}")
    public ApiResponse<Void> delete(@PathVariable String role) {
        roleService.delete(role);
        return ApiResponse.<Void>builder()
                    .build();
    }
}
