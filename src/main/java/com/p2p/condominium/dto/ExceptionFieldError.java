package com.p2p.condominium.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.p2p.condominium.constant.ExceptionResponseConstant.MESSAGES_ERROR;
import static com.p2p.condominium.constant.ExceptionResponseConstant.MESSAGES_FIELD;

@AllArgsConstructor
@Data
public class ExceptionFieldError {

    public ExceptionFieldError(String error) {
        this.error = error;
    }

    @Schema(description = MESSAGES_FIELD)
    private String field;

    @Schema(description = MESSAGES_ERROR)
    private String error;
}
