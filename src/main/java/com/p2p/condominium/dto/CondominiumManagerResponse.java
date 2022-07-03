package com.p2p.condominium.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class CondominiumManagerResponse {
    private String managerId;
    private LocalDate startValidity;
    private LocalDate endValidity;
}
