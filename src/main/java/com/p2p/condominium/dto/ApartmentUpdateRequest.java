package com.p2p.condominium.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import static com.p2p.condominium.constant.ErrorConstant.REQUEST_ID_REQUIRED;

@Data
@NoArgsConstructor
public class ApartmentUpdateRequest extends ApartmentDTO{
    @NotBlank(message = REQUEST_ID_REQUIRED)
    public String id;
}
