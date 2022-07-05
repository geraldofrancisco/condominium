package com.p2p.condominium.exception;

import com.p2p.condominium.dto.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintDeclarationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@Slf4j
public class ConstraintDeclarationExceptionHandler extends CondominiumExceptionHandler {

    @ExceptionHandler({ConstraintDeclarationException.class})
    public Mono<ResponseEntity<ExceptionResponse>> handleBusinessException
            (final ConstraintDeclarationException ex) {
        log.error("There was a constraint error: {}", ex.getMessage());
        return this.getExceptionResponse(BAD_REQUEST,  ex.getMessage());
    }
}
