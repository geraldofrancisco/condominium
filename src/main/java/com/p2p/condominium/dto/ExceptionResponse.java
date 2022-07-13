package com.p2p.condominium.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import static com.p2p.condominium.constant.ExceptionResponseConstant.MESSAGES;
import static com.p2p.condominium.constant.ExceptionResponseConstant.STATUS;
import static com.p2p.condominium.constant.ExceptionResponseConstant.TIMESTAMP;

@Builder
@Data
public class ExceptionResponse {
    @Schema(description = STATUS)
    private Integer status;

    @Schema(description = TIMESTAMP)
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    @Schema(description = MESSAGES)
    private List<ExceptionFieldError> messages;
}
