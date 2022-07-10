package com.p2p.condominium.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BuildingResponse {
    private String id;
    private String condominium;
    private String name;
    private Integer numberOfFloors;
    private Integer numberOfApartmentsPerFloor;
    private Integer numberOfParkingApartment;
    private boolean containsElevator;
}
