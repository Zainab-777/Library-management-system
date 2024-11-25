//package com.java.springBoot.app.Exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import com.java.springBoot.app.Enum.ResponseStatus;
//import com.java.springBoot.app.Class.Response.APIResponse;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestControllerAdvice
//public class ValidationExceptionHandler {
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public APIResponse<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        
//        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//            errors.put(error.getField(), error.getDefaultMessage());
//        }
//
//        return APIResponse.errorValidation(ResponseStatus.VALIDATION_ERROR.getCode(), errors);
//    }
//}
