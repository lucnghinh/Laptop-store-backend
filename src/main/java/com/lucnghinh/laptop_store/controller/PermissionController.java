package com.lucnghinh.laptop_store.controller;

import com.lucnghinh.laptop_store.dto.request.PermissionRequest;
import com.lucnghinh.laptop_store.dto.response.ApiResponse;
import com.lucnghinh.laptop_store.dto.response.PermissionResponse;
import com.lucnghinh.laptop_store.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/permission")
public class PermissionController {
    PermissionService permissionService;

    @PostMapping("/create")
    public ApiResponse<PermissionResponse> CreatePermission(@RequestBody PermissionRequest permissionRequest) {
        return ApiResponse.<PermissionResponse>builder()
                .data(permissionService.CreatePermisson(permissionRequest))
                .build();
    }

    @GetMapping("/getAll")
    public ApiResponse<List<PermissionResponse>> getAllPermissions() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .data(permissionService.getAllPermissions())
                .build();
    }

    @DeleteMapping("/{permission}")
    public ApiResponse<Void> DeletePermission(@PathVariable String permission) {
        permissionService.deletePermission(permission);
        return ApiResponse.<Void>builder()
                .build();
    }


}
