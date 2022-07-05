package com.p2p.condominium.exception;

import com.p2p.condominium.dto.ExceptionFieldError;
import com.p2p.condominium.dto.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintDeclarationException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.List.of;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@Slf4j
public class CondominiumExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    public Mono<ResponseEntity<ExceptionResponse>> handleBusinessException
            (final BusinessException ex) {
        log.error("There was a business error: {}", ex.getError());
        return this.getExceptionResponse(ex.getStatus(),  ex.getError());
    }

    @ExceptionHandler({ConstraintDeclarationException.class})
    public Mono<ResponseEntity<ExceptionResponse>> handleBusinessException
            (final ConstraintDeclarationException ex) {
        log.error("There was a constraint error: {}", ex.getMessage());
        return this.getExceptionResponse(BAD_REQUEST,  ex.getMessage());
    }

    @ExceptionHandler({WebExchangeBindException.class})
    public Mono<ResponseEntity<ExceptionResponse>> handleWebExchange
            (final WebExchangeBindException ex) {
        log.error("There was a constraints errors: {}", ex.getMessage());
        var errors = ex.getFieldErrors().stream()
                .map(e -> new ExceptionFieldError(e.getField(), e.getDefaultMessage()))
                .collect(Collectors.toList());
        return this.getExceptionResponse(BAD_REQUEST, errors);
    }

    private Mono<ResponseEntity<ExceptionResponse>> getExceptionResponse
            (final HttpStatus status, final String message) {
        final var response = ExceptionResponse.builder()
                .messages(of(new ExceptionFieldError(message)))
                .status(status.value())
                .build();
        return Mono.just(ResponseEntity.status(status).body(response));
    }

    private Mono<ResponseEntity<ExceptionResponse>> getExceptionResponse
            (final HttpStatus status, final List<ExceptionFieldError> messages) {
        final var response = ExceptionResponse.builder()
                .messages(messages)
                .status(status.value())
                .build();
        return Mono.just(ResponseEntity.status(status).body(response));
    }
}
