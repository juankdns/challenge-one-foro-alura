package com.foro.advice;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DatosErroresValidacion>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var errorMessage = e.getFieldErrors().stream().map(DatosErroresValidacion::new).toList();
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        var errorMessage = "Error en la solicitud: los datos no son legibles";
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        var errorMessage = "Error en la solicitud: los datos no son legibles";
        return ResponseEntity.badRequest().body(errorMessage);
    }

    public record DatosErroresValidacion(String campo, String mensaje) {
        public DatosErroresValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
