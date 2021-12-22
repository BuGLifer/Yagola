package com.buglifer.yagola.common.handler;

import com.buglifer.yagola.common.dto.ExceptionResponse;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import javax.persistence.EntityNotFoundException;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ExceptionResponse
                .initException()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .exception(e)
                .build());
    }

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<ExceptionResponse> handleSecurityException(SecurityException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                ExceptionResponse
                .initException()
                .httpStatus(HttpStatus.FORBIDDEN)
                .exception(e)
                .build());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<ExceptionResponse> handleHttpServerErrorException(HttpServerErrorException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ExceptionResponse
                        .initException()
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .exception(e)
                        .build());
    }
}
