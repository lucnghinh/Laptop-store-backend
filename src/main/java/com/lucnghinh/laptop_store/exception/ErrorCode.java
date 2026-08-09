package com.lucnghinh.laptop_store.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter

public enum ErrorCode {

    INVALID_KEY(1001, "Invalid message key", HttpStatus.BAD_REQUEST),

    // ERROR PRODUCT
    PRODUCT_ALREADY_EXISTS(1003, "Product exists with name", HttpStatus.CONFLICT),
    PRODUCT_NOT_FOUND(1004, "Product does not exist", HttpStatus.NOT_FOUND),

    PRODUCT_NAME_REQUIRED(1005, "Name required", HttpStatus.BAD_REQUEST),
    PRODUCT_NAME_MAX_LENGTH(1006, "Name must be at most 255 characters", HttpStatus.BAD_REQUEST),

    PRODUCT_DESCRIPTION_MAX_LENGTH(1007, "Description must be at most 2000 characters", HttpStatus.BAD_REQUEST),

    PRODUCT_PRICE_REQUIRED(1008, "Price is required", HttpStatus.BAD_REQUEST),
    PRODUCT_PRICE_INVALID(1009, "Price must be greater than 0", HttpStatus.BAD_REQUEST),

    PRODUCT_DISCOUNT_PRICE_REQUIRED(1010, "discountPrice is required", HttpStatus.BAD_REQUEST),
    PRODUCT_DISCOUNT_PRICE_INVALID(1011, "discountPrice must be greater than 0", HttpStatus.BAD_REQUEST),

    PRODUCT_BRAND_REQUIRED(1012, "Brand is required", HttpStatus.BAD_REQUEST),
    PRODUCT_BRAND_MAX_LENGTH(1013, "Brand must be at most 100 characters", HttpStatus.BAD_REQUEST),

    PRODUCT_CATEGORY_REQUIRED(1014, "Category is required", HttpStatus.BAD_REQUEST),
    PRODUCT_CATEGORY_MAX_LENGTH(1015, "Category must be at most 100 characters", HttpStatus.BAD_REQUEST),

    PRODUCT_SLUG_REQUIRED(1016, "Slug is required", HttpStatus.BAD_REQUEST),
    PRODUCT_SLUG_MAX_LENGTH(1017, "Slug must be at most 255 characters", HttpStatus.BAD_REQUEST),

    PRODUCT_STOCK_INVALID(1018, "Stock must be greater than 0", HttpStatus.BAD_REQUEST),

    PRODUCT_THUMBNAIL_MAX_LENGTH(1019, "Thumbnail must be at most 500 characters", HttpStatus.BAD_REQUEST),

    // ERROR USER
    USER_USERNAME_REQUIRED(1020, "Username is required", HttpStatus.BAD_REQUEST),
    USER_USERNAME_INVALID(1021, "Username must be between {min} and 50 characters", HttpStatus.BAD_REQUEST),

    USER_PASSWORD_REQUIRED(1022, "Password is required", HttpStatus.BAD_REQUEST),
    USER_PASSWORD_INVALID(1023, "Password must be between {min} and 255 characters", HttpStatus.BAD_REQUEST),

    USER_EMAIL_REQUIRED(1024, "Email is required", HttpStatus.BAD_REQUEST),
    USER_EMAIL_INVALID(1025, "Email is invalid", HttpStatus.BAD_REQUEST),

    USER_FIRST_NAME_REQUIRED(1026, "First name is required", HttpStatus.BAD_REQUEST),

    USER_LAST_NAME_REQUIRED(1027, "Last name is required", HttpStatus.BAD_REQUEST),

    USER_DOB_REQUIRED(1028, "Date of birth is required", HttpStatus.BAD_REQUEST),

    USER_USERNAME_ALREADY_EXISTS(1029, "Username already exists", HttpStatus.CONFLICT),

    USER_EMAIL_ALREADY_EXISTS(1030, "Email already exists", HttpStatus.CONFLICT),

    // Unauthenticated
    INVALID_CREDENTIALS(1031, "Username or password is incorrect", HttpStatus.UNAUTHORIZED),
    INVALID_TOKEN(1032, "Invalid token", HttpStatus.UNAUTHORIZED),
    TOKEN_EXPIRED(1033, "Token has expired", HttpStatus.UNAUTHORIZED),

    // OTHER
    USER_USERNAME_NOT_FOUND(1034, "Username not found", HttpStatus.NOT_FOUND),

    ROLE_NOT_FOUND(1035, "Role not found", HttpStatus.NOT_FOUND),
    UNAUTHORIZED(1036, "you do not have permission", HttpStatus.FORBIDDEN),
    UNAUTHENTICATED(1037,"unauthenticated",HttpStatus.UNAUTHORIZED),
    USER_DOB_INVALID(1038, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),

    INVALID_SORT_DIRECTION(1039, "invalid sort direction", HttpStatus.BAD_REQUEST),
    INVALID_SORT_FIELD(1040, "Invalid sort field", HttpStatus.BAD_REQUEST),

    INVALID_PARAMETER_FORMAT(1041,"The parameter '%s' provided is not in the correct data type format.",HttpStatus.BAD_REQUEST),

    FILE_IS_EMPTY(1042, "Uploaded file cannot be empty", HttpStatus.BAD_REQUEST),
    INVALID_FILE_NAME(1043, "Invalid file name or missing file extension", HttpStatus.BAD_REQUEST),
    FILE_UPLOAD_FAILED(1044, "Could not store file. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR),
    UNSUPPORTED_FILE_TYPE(1045, "Only PNG images are allowed", HttpStatus.BAD_REQUEST),
    FILE_TOO_LARGE(1046, "File size exceeds the maximum permitted limit of 5MB", HttpStatus.BAD_REQUEST),
    FILE_DELETE_FAILED(1047,"File does not exist or has been deleted", HttpStatus.NOT_FOUND),
    CATEGORY_ALREADY_EXISTS(1048, "Category exists with name", HttpStatus.CONFLICT),
    CATEGORY_NOT_FOUND(10049, "Category does not exist", HttpStatus.NOT_FOUND)
    ;

    ErrorCode(int code, String message,HttpStatus httpStatus) {
        this.message = message;
        this.code = code;
        this.httpStatus = httpStatus;
    }

    int code;
    String message;
    HttpStatus httpStatus;
}
