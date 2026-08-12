package com.micropool.matchservice.exception;

import com.micropool.matchservice.client.ShotServiceUnavailableException;
import org.springframework.http.HttpStatus;
import com.micropool.matchservice.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ShotServiceUnavailableException.class)
    public ResponseEntity<ErrorResponse> handleShotServiceUnavailable(ShotServiceUnavailableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(new ErrorResponse("SHOT_SERVICE_UNAVAILABLE", "Could not resolve shot outcome, try again"));
    }
}
