package com.test.TestServer.configuration;

import com.test.TestServer.entity.ExceptionResponse;
import com.test.TestServer.exceptions.OwnerNotFoundException;
import com.test.TestServer.exceptions.PetNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({OwnerNotFoundException.class, PetNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleException(OwnerNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.internalServerError().body(ExceptionResponse.builder()
                .errorMessage(e.getMessage())
                .path(request.getRequestURI())
                .status(500L)
                .timestamp(LocalDateTime.now().toString())
                .build());
    }
}
