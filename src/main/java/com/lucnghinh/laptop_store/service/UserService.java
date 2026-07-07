package com.lucnghinh.laptop_store.service;

import com.lucnghinh.laptop_store.dto.response.UserResponse;
import com.lucnghinh.laptop_store.entity.User;
import com.lucnghinh.laptop_store.exception.ErrorCode;
import com.lucnghinh.laptop_store.exception.ResourceNotFoundException;
import com.lucnghinh.laptop_store.mapper.UserMapper;
import com.lucnghinh.laptop_store.repository.RoleRepository;
import com.lucnghinh.laptop_store.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor

@Service
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    RoleRepository roleRepository;

    public UserResponse getMyInfo(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(name).orElseThrow(() ->new ResourceNotFoundException(ErrorCode.USER_USERNAME_NOT_FOUND));

        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUserRoles(String username, Set<String> roles){
        User user = userRepository.findByUsername(username).orElseThrow(() ->new ResourceNotFoundException(ErrorCode.USER_USERNAME_NOT_FOUND));
        var listRoles = roleRepository.findAllById(roles);
        if(listRoles.size() != roles.size()){
            throw new ResourceNotFoundException(ErrorCode.ROLE_NOT_FOUND);
        }
        user.setRoles(new HashSet<>(listRoles));
        user = userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

}
