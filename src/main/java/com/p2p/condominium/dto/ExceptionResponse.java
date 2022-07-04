package com.p2p.condominium.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class ExceptionResponse {
    private Integer status;
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
    private List<ExceptionFieldError> messages;
}
