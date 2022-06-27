package com.p2p.condominium.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import static com.p2p.condominium.constant.ErrorConstant.REQUEST_NAME_REQUIRED;


@Data
@NoArgsConstructor
public class CondominiumInsertRequest extends CondominiumDTO {

    @NotBlank(message = REQUEST_NAME_REQUIRED)
    public String name;


}
