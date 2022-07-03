package com.p2p.condominium.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.p2p.condominium.constant.ErrorConstant.REQUEST_ADDRESS_REQUIRED;
import static com.p2p.condominium.constant.ErrorConstant.REQUEST_CNPJ_INVALIDO;
import static com.p2p.condominium.constant.ErrorConstant.REQUEST_CONSTRUCTION_COMPANY_REQUIRED;
import static com.p2p.condominium.constant.ErrorConstant.REQUEST_IDENTIFICATION_REQUIRED;
import static com.p2p.condominium.constant.ErrorConstant.REQUEST_NAME_REQUIRED;
import static com.p2p.condominium.util.CpfCnpjUtil.removeSpecialCharacters;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CondominiumDTO {
    private String id;

    @CNPJ(message = REQUEST_CNPJ_INVALIDO)
    @NotBlank(message = REQUEST_IDENTIFICATION_REQUIRED)
    private String identification;

    @NotBlank(message = REQUEST_NAME_REQUIRED)
    private String name;

    @NotBlank(message = REQUEST_CONSTRUCTION_COMPANY_REQUIRED)
    private String constructionCompanyId;

    @Valid
    @NotNull(message = REQUEST_ADDRESS_REQUIRED)
    private Address address;

    public String getIdentification() {
        return removeSpecialCharacters(identification);
    }
}
