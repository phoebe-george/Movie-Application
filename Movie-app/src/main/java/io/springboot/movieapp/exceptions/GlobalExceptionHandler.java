package io.springboot.movieapp.exceptions;

import io.springboot.movieapp.domain.dto.response.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse =
                ErrorResponse.builder()
                        .message(ex.getMessage())
                .path(request.getDescription(false))
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .timestamp(String.valueOf(System.currentTimeMillis()))
                        .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException ex, WebRequest request) {
        ErrorResponse errorResponse =
                ErrorResponse.builder()
                        .message("An unexpected error occurred")
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .path(request.getDescription(false))
                        .timestamp(String.valueOf(System.currentTimeMillis()))
                        .build();
        log.error("NullPointerException: {}", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // Handle validation exceptions (e.g., MethodArgumentNotValidException)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .findFirst()
                .orElse(ex.getMessage());

        ErrorResponse errorResponse =
                ErrorResponse.builder()
                        .message(errorMessage)
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .path(request.getDescription(false))
                        .timestamp(String.valueOf(System.currentTimeMillis()))
                        .build();
        log.error("MethodArgumentNotValidException: {}", errorMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        ErrorResponse errorResponse =
                ErrorResponse.builder()
                        .message("Data integrity violation")
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .path(request.getDescription(false))
                        .timestamp(String.valueOf(System.currentTimeMillis()))
                        .build();

        log.error("DataIntegrityViolationException: {}", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> UsernameNotFoundException(RuntimeException ex, WebRequest request) {
        ErrorResponse errorResponse =
                ErrorResponse.builder()
                        .message(ex.getMessage())
                        .statusCode(HttpStatus.UNAUTHORIZED.value())
                        .path(request.getDescription(false))
                        .timestamp(String.valueOf(System.currentTimeMillis()))
                        .build();
        log.error("RuntimeException: {}", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED );
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> AccessDeniedException(RuntimeException ex, WebRequest request) {
        ErrorResponse errorResponse =
                ErrorResponse.builder()
                        .message(ex.getMessage())
                        .statusCode(HttpStatus.UNAUTHORIZED.value())
                        .path(request.getDescription(false))
                        .timestamp(String.valueOf(System.currentTimeMillis()))
                        .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex, WebRequest request) {
        ErrorResponse errorResponse =
                ErrorResponse.builder()
                        .message(ex.getMessage())
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .path(request.getDescription(false))
                        .timestamp(String.valueOf(System.currentTimeMillis()))
                        .build();
        log.error("RuntimeException: {}", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, WebRequest request) {
        ErrorResponse errorResponse =
                ErrorResponse.builder()
                        .message("An unexpected error occurred")
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .path(request.getDescription(false))
                        .timestamp(String.valueOf(System.currentTimeMillis()))
                        .build();
        log.error("Exception: {}", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
