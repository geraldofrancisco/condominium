package com.p2p.condominium.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import static com.p2p.condominium.constant.ErrorConstant.APARTMENT_ID_REQUIRED;
import static com.p2p.condominium.constant.ErrorConstant.APARTMENT_OWNER_REQUIRED;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApartmentOwnerRequest {
    @NotBlank(message = APARTMENT_ID_REQUIRED)
    public String id;

    @NotBlank(message = APARTMENT_OWNER_REQUIRED)
    private String owner;
}
