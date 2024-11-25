package com.java.springBoot.app.Enum;



public enum ResponseStatus {
    SUCCESS(200, "Operation Successful"),
    NOT_FOUND(404, "Resource Not Found"),
    VALIDATION_ERROR(400, "Validation Error"),
    INTERNAL_ERROR(500, "Internal Server Error"),
    UNAUTHORIZED(401, "Unauthorized Access"),
	USERNAME_TAKEN(1001,"Username is already taken.");

    private final int code;
    private final String message;

    ResponseStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
    
}
