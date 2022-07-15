package com.p2p.condominium.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ApartmentResponse {
    private String id;
    private String building;
    private String owner;
    private Integer floor;
    private Integer number;
}
