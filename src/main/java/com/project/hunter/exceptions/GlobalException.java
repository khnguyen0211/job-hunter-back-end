package com.project.hunter.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.hunter.domain.dto.RestResponse;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = {UsernameNotFoundException.class, BadCredentialsException.class})
    public ResponseEntity<RestResponse<Object>> handleJwtException(Exception exception) {
        RestResponse<Object> response =
                new RestResponse<>(400, "Call API Failed", null, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<RestResponse<List<String>>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException methodArgumentNotValidException) {

        BindingResult createUserBindingResult = methodArgumentNotValidException.getBindingResult();
        List<FieldError> errors = createUserBindingResult.getFieldErrors();
        List<String> errorMessages = new ArrayList<>();
        for (FieldError errorField : errors) {
            errorMessages.add(errorField.getDefaultMessage());
        }
        RestResponse<List<String>> restResponseError =
                new RestResponse<>(400, "Invalid Error", null, errorMessages);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restResponseError);
    }

}
