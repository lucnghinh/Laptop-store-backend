package com.lucnghinh.laptop_store.service;


import com.lucnghinh.laptop_store.dto.request.RoleRequest;
import com.lucnghinh.laptop_store.dto.response.RoleResponse;
import com.lucnghinh.laptop_store.mapper.RoleMapper;
import com.lucnghinh.laptop_store.repository.PermissionRepository;
import com.lucnghinh.laptop_store.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)

public class RoleService {
    RoleRepository  roleRepository;
    RoleMapper roleMapper;
    PermissionRepository permissionRepository;

    public RoleResponse create(RoleRequest roleRequest) {
        var role = roleMapper.toRole(roleRequest);
        var permissions = permissionRepository.findAllById(roleRequest.getPermissions());

        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> getAll() {
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }

    public void delete(String name) {
        roleRepository.deleteById(name);
    }
}
