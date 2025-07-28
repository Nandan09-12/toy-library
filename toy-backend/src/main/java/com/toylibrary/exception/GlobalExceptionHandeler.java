package com.toylibrary.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.toylibrary.exception.ApiErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandeler {
    

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> handleUserAlreadyExists(UserAlreadyExistsException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("error", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MemberTypeNotApplicableException.class)
        public ResponseEntity<Object> handleMemberTypeNotApplicable(MemberTypeNotApplicableException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ToyNotFoundException.class)
    public ResponseEntity<String> handleToyNotFound(ToyNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotAllowedToListToyException.class)
    public ResponseEntity<ApiErrorResponse> handleUserNotAllowed(UserNotAllowedToListToyException ex, HttpServletRequest request) {
        ApiErrorResponse error = new ApiErrorResponse(
        LocalDateTime.now(),
        List.of(ex.getMessage()),
        request.getRequestURI()
        );
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ToyAlreadyRentedException.class)
    public ResponseEntity<ApiErrorResponse> handleToyAlreadyRented(ToyAlreadyRentedException ex, HttpServletRequest request) {
    ApiErrorResponse error = new ApiErrorResponse(
        LocalDateTime.now(),
        List.of(ex.getMessage()),
        request.getRequestURI()
    );
    return new ResponseEntity<>(error, HttpStatus.CONFLICT);
}

    @ExceptionHandler(UserNotEligibleToRentException.class)
    public ResponseEntity<ApiErrorResponse> handleNotEligible(UserNotEligibleToRentException ex, HttpServletRequest request) {
        ApiErrorResponse error = new ApiErrorResponse(
            LocalDateTime.now(),
            List.of(ex.getMessage()),
            request.getRequestURI()
        );
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InsufficientPointsException.class)
    public ResponseEntity<ApiErrorResponse> handleInsufficientPoints(InsufficientPointsException ex, HttpServletRequest request) {
        ApiErrorResponse error = new ApiErrorResponse(
            LocalDateTime.now(),
            List.of(ex.getMessage()),
            request.getRequestURI()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(RentalNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handelRentalNotFoundException(RentalNotFoundException ex, HttpServletRequest request) {
        ApiErrorResponse error = new ApiErrorResponse(
            LocalDateTime.now(),
            List.of(ex.getMessage()),
            request.getRequestURI()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RentalAlreadyReturnedException.class)
    public ResponseEntity<ApiErrorResponse>RentalAlreadyReturnedException(RentalAlreadyReturnedException ex, HttpServletRequest request) {
        ApiErrorResponse error = new ApiErrorResponse(
            LocalDateTime.now(),
            List.of(ex.getMessage()),
            request.getRequestURI()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CannotRentOwnToyException.class)
    public ResponseEntity<ApiErrorResponse> handleCannotRentOwnToy(CannotRentOwnToyException ex, HttpServletRequest request) {
        ApiErrorResponse error = new ApiErrorResponse(
            LocalDateTime.now(),
            List.of(ex.getMessage()),
            request.getRequestURI()
        );
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<ApiErrorResponse> handleUnauthorizedAccess(UnauthorizedAccessException ex, HttpServletRequest request) {
        ApiErrorResponse error = new ApiErrorResponse(
            LocalDateTime.now(),
            List.of(ex.getMessage()),
            request.getRequestURI()
        );
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

















}
