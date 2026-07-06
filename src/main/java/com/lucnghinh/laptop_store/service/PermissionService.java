package com.lucnghinh.laptop_store.service;

import com.lucnghinh.laptop_store.dto.request.PermissionRequest;
import com.lucnghinh.laptop_store.dto.response.PermissionResponse;
import com.lucnghinh.laptop_store.entity.Permission;
import com.lucnghinh.laptop_store.mapper.PermissionMapper;
import com.lucnghinh.laptop_store.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse CreatePermisson(PermissionRequest permissionRequest) {
        Permission permission = permissionMapper.toPermission(permissionRequest);
        permission = (permissionRepository.save(permission));
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAllPermissions() {
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public void deletePermission(String permission) {
        permissionRepository.deleteById(permission);
    }

}
