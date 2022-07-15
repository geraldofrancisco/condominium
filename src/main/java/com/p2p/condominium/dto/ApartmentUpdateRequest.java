package com.p2p.condominium.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.p2p.condominium.constant.ErrorConstant.REQUEST_ID_REQUIRED;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApartmentUpdateRequest  {
    @NotBlank(message = REQUEST_ID_REQUIRED)
    public String id;

    @NotBlank
    private String building;

    @NotNull
    private Integer floor;

    @NotNull
    private Integer number;
}
