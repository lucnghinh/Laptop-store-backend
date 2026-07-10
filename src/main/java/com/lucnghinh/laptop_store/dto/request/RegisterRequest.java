package com.lucnghinh.laptop_store.dto.request;

import com.lucnghinh.laptop_store.validator.DobConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "USER_USERNAME_REQUIRED")
    @Size(min = 3, max = 50, message = "USER_USERNAME_INVALID")
    String username;

    @NotBlank(message = "USER_PASSWORD_REQUIRED")
    @Size(min = 8, max = 255, message = "USER_PASSWORD_INVALID")
    String password;

    @NotBlank(message = "USER_EMAIL_REQUIRED")
    @Email(message = "USER_EMAIL_INVALID")
    String email;

    @NotBlank(message = "USER_FIRST_NAME_REQUIRED")
    String firstName;

    @NotBlank(message = "USER_LAST_NAME_REQUIRED")
    String lastName;

    @NotNull(message = "USER_DOB_REQUIRED")
    @DobConstraint(min = 18, message = "USER_DOB_INVALID")
    LocalDate dob;
}
