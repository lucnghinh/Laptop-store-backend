package com.lucnghinh.laptop_store.exception;

public enum ErrorCode {
    PRODUCT_EXISTED(1003,"Product exists with name"),
    PRODUCT_DOES_NOT_EXIST(1004,"Product does not exist"),

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

    INVALID_KEY(1001,"Invalid message key")
    ;
    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
