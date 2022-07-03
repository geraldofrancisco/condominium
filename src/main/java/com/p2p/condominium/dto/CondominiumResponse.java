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
public class CondominiumResponse {
    private String id;
    private String identification;
    private String name;
    private String constructionCompanyId;
    private CondominiumManagerResponse condominiumManager;
    private AddressResponse address;
}
