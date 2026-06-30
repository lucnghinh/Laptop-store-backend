package com.lucnghinh.laptop_store.exception;

public class DuplicateResourceException extends RuntimeException {
    private ErrorCode errorCode;


    public DuplicateResourceException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;

    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}

