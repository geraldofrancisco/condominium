package com.p2p.condominium.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class BusinessException extends RuntimeException {
    @Getter
    private String error;
}
