package com.p2p.condominium.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@AllArgsConstructor
public class BusinessException extends RuntimeException {

    public BusinessException(String error) {
        this.error = error;
        this.status = BAD_REQUEST;
    }

    @Getter
    private String error;

    @Getter
    private HttpStatus status;
}
