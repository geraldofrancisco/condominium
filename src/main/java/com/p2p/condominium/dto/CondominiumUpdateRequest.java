package com.p2p.condominium.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import static com.p2p.condominium.constant.ErrorConstant.REQUEST_CONSTRUCTION_COMPANY_REQUIRED;
import static com.p2p.condominium.constant.ErrorConstant.REQUEST_ID_REQUIRED;
import static com.p2p.condominium.constant.ErrorConstant.REQUEST_NAME_REQUIRED;


@Data
@NoArgsConstructor
public class CondominiumUpdateRequest extends CondominiumDTO {

    @NotBlank(message = REQUEST_ID_REQUIRED)
    public String id;

    @NotBlank(message = REQUEST_NAME_REQUIRED)
    public String name;

    @NotBlank(message = REQUEST_CONSTRUCTION_COMPANY_REQUIRED)
    private String constructionCompanyId;
}
