package com.hicode.cozastore.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {
    // BadRequest
    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<?> badRequestException(@NotNull BadRequestException e) {
        ErrorMessage apiException = new ErrorMessage(e.getMessage(), HttpStatus.BAD_REQUEST.value(), ZonedDateTime.now(ZoneId.of("Z")));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiException);
    }

    //401 - Unauthorized
    @ExceptionHandler(value = {UnauthorizedException.class})
    public ResponseEntity<?> unauthorizedException(UnauthorizedException e) {
        ErrorMessage apiException = new ErrorMessage(e.getMessage(), HttpStatus.UNAUTHORIZED.value(), ZonedDateTime.now(ZoneId.of("Z")));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiException);
    }

    //403 - Forbidden
    @ExceptionHandler(value = {ForbiddenException.class})
    public ResponseEntity<?> forbiddenException(ForbiddenException e) {
        ErrorMessage apiException = new ErrorMessage(e.getMessage(), HttpStatus.FORBIDDEN.value(), ZonedDateTime.now(ZoneId.of("Z")));
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiException);
    }

    //500 - Internal Server Error
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> serverException(Exception e) {
        ErrorMessage apiException = new ErrorMessage(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), ZonedDateTime.now(ZoneId.of("Z")));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiException);
    }

    //404 - Not found
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<?> notFoundException(NotFoundException e) {
        ErrorMessage apiException = new ErrorMessage(e.getMessage(), HttpStatus.NOT_FOUND.value(), ZonedDateTime.now(ZoneId.of("Z")));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiException);
    }

    public static class NotFoundException extends RuntimeException {
        public NotFoundException(String message) {
            super(message);
        }


    }

    public static class BadRequestException extends RuntimeException {
        public BadRequestException(String message) {
            super(message);
        }

    }

    public static class UnauthorizedException extends RuntimeException {
        public UnauthorizedException(String message) {
            super(message);
        }
    }

    public static class ForbiddenException extends RuntimeException {
        public ForbiddenException(String message) {
            super(message);
        }
    }
}

//Class MessageError
@RequiredArgsConstructor
@Data
class ErrorMessage {
    private final String message;
    private final int status;
    private final ZonedDateTime timestamp;

}
