package com.lucnghinh.laptop_store.mapper;

import com.lucnghinh.laptop_store.dto.request.RegisterRequest;
import com.lucnghinh.laptop_store.dto.response.UserResponse;
import com.lucnghinh.laptop_store.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toUser(RegisterRequest registerRequest);

    UserResponse toUserResponse(User user);
}
