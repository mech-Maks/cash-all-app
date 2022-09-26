package com.tinkoff.com.tinkoff.financialtracker.validation;

import com.tinkoff.com.tinkoff.financialtracker.exception.AuthException;
import com.tinkoff.com.tinkoff.financialtracker.utils.ApiErrorResponse;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@ResponseBody
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<Object> handleConversionFailedException(ConversionFailedException ex, WebRequest request) {
        logger.error("ConversionFailedException happened");

        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ApiErrorResponse> errors = new ArrayList<>(ex.getFieldErrors().size());
        for (FieldError error : ex.getFieldErrors()) {
            errors.add(ApiErrorResponse.builder()
                    .errorCode(HttpStatus.BAD_REQUEST.name())
                    .errorMessage(String.format("field %s - %s", error.getField(), error.getDefaultMessage()))
                    .build());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

    @ExceptionHandler(value = AuthException.class)
    protected ResponseEntity<Object> handleAuthException(AuthException ex, WebRequest request) {
        System.out.println("AuthException occurred");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("success", false, "message", ex.getMessage()));
    }
}
