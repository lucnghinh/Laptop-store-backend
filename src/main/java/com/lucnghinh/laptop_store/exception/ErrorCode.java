package com.lucnghinh.laptop_store.exception;

public enum ErrorCode {
    //ERROR KEYENUM
    INVALID_KEY(1001,"Invalid message key"),


    //ERROR PRODUCT
    PRODUCT_ALREADY_EXISTS(1003,"Product exists with name"),
    PRODUCT_NOT_FOUND(1004,"Product does not exist"),

    PRODUCT_NAME_REQUIRED(1005,"Name required"),
    PRODUCT_NAME_MAX_LENGTH(1006, "Name must be at most 255 characters"),

    PRODUCT_DESCRIPTION_MAX_LENGTH(1007, "Description must be at most 2000 characters"),

    PRODUCT_PRICE_REQUIRED(1008, "Price is required"),
    PRODUCT_PRICE_INVALID(1009, "Price must be greater than 0"),

    PRODUCT_DISCOUNT_PRICE_REQUIRED(1010, "discountPrice is required"),
    PRODUCT_DISCOUNT_PRICE_INVALID(1011, "discountPrice must be greater than 0"),

    PRODUCT_BRAND_REQUIRED(1012, "Brand is required"),
    PRODUCT_BRAND_MAX_LENGTH(1013, "Brand must be at most 100 characters"),

    PRODUCT_CATEGORY_REQUIRED(1014, "Category is required"),
    PRODUCT_CATEGORY_MAX_LENGTH(1015, "Category must be at most 100 characters"),

    PRODUCT_SLUG_REQUIRED(1016, "Slug is required"),
    PRODUCT_SLUG_MAX_LENGTH(1017, "Slug must be at most 255 characters"),

    PRODUCT_STOCK_INVALID(1018, "Stock must be greater than 0"),

    PRODUCT_THUMBNAIL_MAX_LENGTH(1019, "Thumbnail must be at most 500 characters"),

    //ERROR USER
    USER_USERNAME_REQUIRED(1020, "Username is required"),
    USER_USERNAME_INVALID(1021, "Username must be between 3 and 50 characters"),

    USER_PASSWORD_REQUIRED(1022, "Password is required"),
    USER_PASSWORD_INVALID(1023, "Password must be between 8 and 255 characters"),


    USER_EMAIL_REQUIRED(1024, "Email is required"),
    USER_EMAIL_INVALID(1025, "Email is invalid"),

    USER_FIRST_NAME_REQUIRED(1026, "First name is required"),

    USER_LAST_NAME_REQUIRED(1027, "Last name is required"),

    USER_DOB_REQUIRED(1028, "Date of birth is required"),

    USER_USERNAME_ALREADY_EXISTS(1029, "Username already exists"),

    USER_EMAIL_ALREADY_EXISTS(1030, "Email already exists"),



//    Unauthenticated
    INVALID_CREDENTIALS(1031, "Username or password is incorrect"),
    INVALID_TOKEN(1032, "Invalid token"),
    TOKEN_EXPIRED(1033, "Token has expired");
    ;
    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }


    public int getCode() {
        return code;
    }

}
