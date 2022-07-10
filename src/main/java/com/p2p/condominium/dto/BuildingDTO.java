package com.p2p.condominium.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import static com.p2p.condominium.constant.ErrorConstant.BUILDING_CONDOMINIUM_REQUIRED;
import static com.p2p.condominium.constant.ErrorConstant.BUILDING_NAME_CONDOMINIUM_REQUIRED;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BuildingDTO {
    private String id;

    private String condominium;

    private String name;

    private Integer numberOfFloors;

    private Integer numberOfApartmentsPerFloor;

    private Integer numberOfParkingApartment;

    private boolean containsElevator;
}
