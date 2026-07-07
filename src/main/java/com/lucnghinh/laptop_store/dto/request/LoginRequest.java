package com.lucnghinh.laptop_store.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "USER_USERNAME_REQUIRED")
    @Size(min = 3, max = 50, message = "USER_USERNAME_INVALID")
    String username;

    @NotBlank (message = "USER_PASSWORD_REQUIRED")
    @Size(min = 8, max = 255, message = "USER_PASSWORD_INVALID")
    String password;
}
