package com.p2p.condominium.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ExceptionFieldError {

    public ExceptionFieldError(String error) {
        this.error = error;
    }

    private String field;
    private String error;
}
