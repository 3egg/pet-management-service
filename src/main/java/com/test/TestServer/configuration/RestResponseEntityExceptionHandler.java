package com.test.TestServer.configuration;

import com.test.TestServer.exception.ErrorMessage;
import com.test.TestServer.exception.OwnerNotFoundException;
import com.test.TestServer.exception.PetAlreadyHasOwnerException;
import com.test.TestServer.exception.PetNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {


    @ExceptionHandler({OwnerNotFoundException.class, PetAlreadyHasOwnerException.class, PetNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleException(
            Exception ex, WebRequest request) {
        return ResponseEntity.internalServerError()
                .body(ErrorMessage.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .path(((ServletWebRequest)request).getRequest().getRequestURI())
                        .timestamp(Instant.now())
                        .error(ex.getMessage())
                        .build());
    }


}