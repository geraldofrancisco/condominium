package com.p2p.condominium.dto;

import com.p2p.condominium.enums.StateEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import static com.p2p.condominium.constant.AddressConstant.CITY;
import static com.p2p.condominium.constant.AddressConstant.COMPLEMENT;
import static com.p2p.condominium.constant.AddressConstant.DISTRICT;
import static com.p2p.condominium.constant.AddressConstant.NUMBER;
import static com.p2p.condominium.constant.AddressConstant.STATE;
import static com.p2p.condominium.constant.AddressConstant.STREET;
import static com.p2p.condominium.constant.AddressConstant.ZIPCODE;

@Builder
@Data
public class Address {
    @Schema(description = ZIPCODE)
    private String zipCode;

    @Schema(description = STREET)
    private String street;

    @Schema(description = NUMBER)
    private Integer number;

    @Schema(description = COMPLEMENT)
    private String complement;

    @Schema(description = DISTRICT)
    private String district;

    @Schema(description = CITY)
    private String city;

    @Schema(description = STATE)
    private StateEnum state;
}
