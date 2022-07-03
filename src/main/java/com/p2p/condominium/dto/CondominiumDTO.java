package com.p2p.condominium.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.p2p.condominium.constant.ErrorConstant.REQUEST_ADDRESS_REQUIRED;
import static com.p2p.condominium.constant.ErrorConstant.REQUEST_CONSTRUCTION_COMPANY_REQUIRED;
import static com.p2p.condominium.constant.ErrorConstant.REQUEST_NAME_REQUIRED;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CondominiumDTO {
    private String id;

    @NotBlank(message = REQUEST_NAME_REQUIRED)
    private String name;

    @NotBlank(message = REQUEST_CONSTRUCTION_COMPANY_REQUIRED)
    private String constructionCompanyId;
    private String condominiumManagerId;

    @Valid
    @NotNull(message = REQUEST_ADDRESS_REQUIRED)
    private Address address;
}
