package com.p2p.condominium.exception;

import com.p2p.condominium.dto.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
@Slf4j
public class BusinessExceptionHandler extends CondominiumExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    public Mono<ResponseEntity<ExceptionResponse>> handleBusinessException
            (final BusinessException ex) {
        log.error("There was a business error: {}", ex.getError());
        return this.getExceptionResponse(ex.getStatus(),  ex.getError());
    }
}
