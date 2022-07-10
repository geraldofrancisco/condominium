package com.p2p.condominium.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ApartmentDTO {
    private String id;
    @NotBlank
    private String building;
    @NotBlank
    private String owner;
    @NotNull
    private Integer floor;
    @NotNull
    private Integer number;
}
