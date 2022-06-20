package com.p2p.condominium.exception;

import com.p2p.condominium.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public abstract class CondominiumExceptionHandler {
    protected Mono<ResponseEntity<ExceptionResponse>> getExceptionResponse
            (final HttpStatus status, String message) {
        final var response = ExceptionResponse.builder()
                .message(message)
                .status(status.value())
                .build();
        return Mono.just(ResponseEntity.status(status).body(response));
    }
}
