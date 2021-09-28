package com.lean.controller;

import com.lean.domain.adapters.GenericError;
import com.lean.domain.exception.ControlException;
import com.lean.domain.exception.GeneralException;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Clase de excepciones de las controladores.
 * 
 * @author <b>Developer</b>: Cesar Olivares<br />
 *         <b>Cambios</b>:<br />
 *         <ul>
 *            <li>25-09-2021 - Creaci&oacute;n del proyecto</li>
 *         </ul>
 * @version 1.0
 *
 */
@Slf4j
@ControllerAdvice
public class ControlleAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("FIELDVALIDATION EXCEPTION");
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(new GenericError(1000, 
                errors), 
                headers,
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Excepcion de validaciones.
     * @param e excepcion capturada
     * @return error personalizado
     * @see GenericError
     */
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<GenericError> generalException(GeneralException e) {
        log.error("GENERAL EXCEPTION");
        return new ResponseEntity<>(new GenericError(1000, 
                e.getMensaje()), 
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Excepcion de errores controlados.
     * @param e excepcion capturada
     * @return error personalizado
     * @see GenericError
     */
    @ExceptionHandler(ControlException.class)
    public ResponseEntity<GenericError> controlException(ControlException e) {
        log.error("CONTROL EXCEPTION", e);
        return new ResponseEntity<>(new GenericError(2000, 
                "Error controlado"), 
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Excepcion de errores no controlados.
     * @param e excepcion capturada
     * @return error personalizado
     * @see GenericError
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericError> exception(Exception e) {
        log.error("CONTROL EXCEPTION", e);
        return new ResponseEntity<>(new GenericError(3000, 
                "Error controlado"), 
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
