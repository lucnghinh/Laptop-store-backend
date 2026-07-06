package com.lucnghinh.laptop_store.mapper;

import com.lucnghinh.laptop_store.dto.request.RoleRequest;
import com.lucnghinh.laptop_store.dto.response.RoleResponse;
import com.lucnghinh.laptop_store.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest roleRequest);

    RoleResponse toRoleResponse(Role role);
}
