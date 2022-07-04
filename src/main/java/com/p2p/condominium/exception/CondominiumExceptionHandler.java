package com.p2p.condominium.exception;

import com.p2p.condominium.dto.ExceptionFieldError;
import com.p2p.condominium.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.List;

import static java.util.List.of;

public abstract class CondominiumExceptionHandler {
    protected Mono<ResponseEntity<ExceptionResponse>> getExceptionResponse
            (final HttpStatus status, final String message) {
        final var response = ExceptionResponse.builder()
                .messages(of(new ExceptionFieldError(message)))
                .status(status.value())
                .build();
        return Mono.just(ResponseEntity.status(status).body(response));
    }

    protected Mono<ResponseEntity<ExceptionResponse>> getExceptionResponse
            (final HttpStatus status, final List<ExceptionFieldError> messages) {
        final var response = ExceptionResponse.builder()
                .messages(messages)
                .status(status.value())
                .build();
        return Mono.just(ResponseEntity.status(status).body(response));
    }
}
