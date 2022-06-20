package com.p2p.condominium.dto;

import com.p2p.condominium.enums.StateEnum;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Address {
    private String zipCode;
    private String street;
    private Integer number;
    private String complement;
    private String district;
    private String city;
    private StateEnum state;
}
