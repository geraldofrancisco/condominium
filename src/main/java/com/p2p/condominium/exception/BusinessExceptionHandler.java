package com.p2p.condominium.exception;

import com.p2p.condominium.dto.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@Slf4j
public class BusinessExceptionHandler extends CondominiumExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    public Mono<ResponseEntity<ExceptionResponse>> handleBusinessException
            (final BusinessException ex) {
        log.error("There was a business error: {}", ex.getError());
        return this.getExceptionResponse(BAD_REQUEST,  ex.getError());
    }
}
