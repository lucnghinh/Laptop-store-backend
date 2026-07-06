package com.lucnghinh.laptop_store.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserResponse {
    Long id;
    String username;
    String firstName;
    String lastName;
    LocalDate dob;
//    Role role;
}
