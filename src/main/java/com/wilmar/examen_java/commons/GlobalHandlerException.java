package com.wilmar.examen_java.commons;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(ValidationCrudException.class)
    public ResponseEntity<CommonResponse<?>> handleValidationCrudException(ValidationCrudException vce) {
        return ResponseEntity.badRequest().body(new CommonResponse<>(vce.getMessage(), false, null));
    }

    @ExceptionHandler(NotFoundCrudException.class)
    public ResponseEntity<CommonResponse<?>> handleNotFoundCrudException(NotFoundCrudException vce) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new CommonResponse<>(vce.getMessage(), false, null));
    }

}
