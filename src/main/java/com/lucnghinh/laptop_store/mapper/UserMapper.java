package com.lucnghinh.laptop_store.mapper;

import com.lucnghinh.laptop_store.dto.request.RegisterRequest;
import com.lucnghinh.laptop_store.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(RegisterRequest registerRequest);
}
