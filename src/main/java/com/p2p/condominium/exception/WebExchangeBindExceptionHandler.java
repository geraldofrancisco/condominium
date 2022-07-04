package com.p2p.condominium.exception;

import com.p2p.condominium.dto.ExceptionFieldError;
import com.p2p.condominium.dto.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@Slf4j
public class WebExchangeBindExceptionHandler extends CondominiumExceptionHandler{
    @ExceptionHandler({WebExchangeBindException.class})
    public Mono<ResponseEntity<ExceptionResponse>> handleWebExchange
            (final WebExchangeBindException ex) {
        log.error("There was a constraints errors: {}", ex.getMessage());
        var errors = ex.getFieldErrors().stream()
                .map(e -> new ExceptionFieldError(e.getField(), e.getDefaultMessage()))
                .collect(Collectors.toList());
        return this.getExceptionResponse(BAD_REQUEST, errors);
    }
}
