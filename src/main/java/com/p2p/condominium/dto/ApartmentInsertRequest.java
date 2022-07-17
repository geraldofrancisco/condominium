package com.p2p.condominium.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.p2p.condominium.constant.ErrorConstant.APARTMENT_BUILDING_REQUIRED;
import static com.p2p.condominium.constant.ErrorConstant.APARTMENT_FLOOR_MIN;
import static com.p2p.condominium.constant.ErrorConstant.APARTMENT_FLOOR_REQUIRED;
import static com.p2p.condominium.constant.ErrorConstant.APARTMENT_NUMBER_MIN;
import static com.p2p.condominium.constant.ErrorConstant.APARTMENT_NUMBER_REQUIRED;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApartmentInsertRequest {
    @NotBlank(message = APARTMENT_BUILDING_REQUIRED)
    private String building;

    @NotNull(message = APARTMENT_FLOOR_REQUIRED)
    @Min(value = 1, message = APARTMENT_FLOOR_MIN)
    private Integer floor;

    @NotNull(message = APARTMENT_NUMBER_REQUIRED)
    @Min(value = 1, message = APARTMENT_NUMBER_MIN)
    private Integer number;
}
