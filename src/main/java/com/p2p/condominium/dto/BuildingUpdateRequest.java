package com.p2p.condominium.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.p2p.condominium.constant.ErrorConstant.BUILDING_CONDOMINIUM_REQUIRED;
import static com.p2p.condominium.constant.ErrorConstant.BUILDING_NAME_CONDOMINIUM_REQUIRED;
import static com.p2p.condominium.constant.ErrorConstant.BUILDING_NUMBER_OF_APARTAMENTS_PER_FLOOR_REQUIRED;
import static com.p2p.condominium.constant.ErrorConstant.BUILDING_NUMBER_OF_FLOORS_REQUIRED;
import static com.p2p.condominium.constant.ErrorConstant.BUILDING_NUMBER_OF_PARKING_APARTMENT_REQUIRED;
import static com.p2p.condominium.constant.ErrorConstant.REQUEST_ID_REQUIRED;

@Data
@NoArgsConstructor
public class BuildingUpdateRequest {

    @NotBlank(message = REQUEST_ID_REQUIRED)
    public String id;

    @NotBlank(message = BUILDING_CONDOMINIUM_REQUIRED)
    private String condominium;

    @NotBlank(message = BUILDING_NAME_CONDOMINIUM_REQUIRED)
    private String name;

    @NotNull(message = BUILDING_NUMBER_OF_FLOORS_REQUIRED)
    private Integer numberOfFloors;

    @NotNull(message = BUILDING_NUMBER_OF_APARTAMENTS_PER_FLOOR_REQUIRED)
    private Integer numberOfApartmentsPerFloor;

    @NotNull(message = BUILDING_NUMBER_OF_PARKING_APARTMENT_REQUIRED)
    private Integer numberOfParkingApartment;

    private boolean containsElevator;
}
