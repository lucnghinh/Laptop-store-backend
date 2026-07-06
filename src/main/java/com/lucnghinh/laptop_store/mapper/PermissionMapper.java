package com.lucnghinh.laptop_store.mapper;

import com.lucnghinh.laptop_store.dto.request.PermissionRequest;
import com.lucnghinh.laptop_store.dto.response.PermissionResponse;
import com.lucnghinh.laptop_store.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
