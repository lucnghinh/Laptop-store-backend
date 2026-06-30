package com.lucnghinh.laptop_store.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "USER_USERNAME_REQUIRED")
    @Size(min = 3, max = 50, message = "USER_USERNAME_INVALID")
    private String username;

    @NotBlank(message = "USER_PASSWORD_REQUIRED")
    @Size(min = 8, max = 255, message = "USER_PASSWORD_INVALID")
    private String password;

    @NotBlank(message = "USER_EMAIL_REQUIRED")
    @Email(message = "USER_EMAIL_INVALID")
    private String email;

    @NotBlank(message = "USER_FIRST_NAME_REQUIRED")
    private String firstName;

    @NotBlank(message = "USER_LAST_NAME_REQUIRED")
    private String lastName;

    @NotNull(message = "USER_DOB_REQUIRED")
    private LocalDate dob;
}
