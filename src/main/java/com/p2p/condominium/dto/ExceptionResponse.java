package com.p2p.condominium.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import static com.p2p.condominium.constant.ExceptionResponseConstant.MESSAGES;
import static com.p2p.condominium.constant.ExceptionResponseConstant.STATUS;
import static com.p2p.condominium.constant.ExceptionResponseConstant.TIMESTAMP;
import static com.p2p.condominium.constant.PatternConstant.DATE_TIME_PATTERN;

@Builder
@Data
public class ExceptionResponse {
    @Schema(description = STATUS)
    private Integer status;

    @Schema(description = TIMESTAMP)
    @Builder.Default
    @JsonFormat(pattern = DATE_TIME_PATTERN)
    private LocalDateTime timestamp = LocalDateTime.now();

    @Schema(description = MESSAGES)
    private List<ExceptionFieldError> messages;
}
