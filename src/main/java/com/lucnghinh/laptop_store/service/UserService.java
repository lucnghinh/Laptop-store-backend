package com.lucnghinh.laptop_store.service;

import com.lucnghinh.laptop_store.dto.response.UserResponse;
import com.lucnghinh.laptop_store.entity.User;
import com.lucnghinh.laptop_store.exception.ErrorCode;
import com.lucnghinh.laptop_store.exception.ResourceNotFoundException;
import com.lucnghinh.laptop_store.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor

@Service
public class UserService {
    UserRepository userRepository;

    public UserResponse getMyInfo(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(name).orElseThrow(() ->new ResourceNotFoundException(ErrorCode.USER_USERNAME_NOT_FOUND));

        return UserMapper(user);

    }

    private UserResponse UserMapper(User user){
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dob(user.getDob())
//                .role(user.getRole())
                .build();
    }

}
