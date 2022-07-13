package com.p2p.condominium.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.p2p.condominium.enums.TypePersonEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

import static com.p2p.condominium.constant.ErrorConstant.REQUEST_CNPJ_INVALIDO;
import static com.p2p.condominium.constant.ErrorConstant.REQUEST_CPF_INVALIDO;
import static com.p2p.condominium.constant.ErrorConstant.REQUEST_EMAIL_INVALID;
import static com.p2p.condominium.constant.ErrorConstant.REQUEST_EMAIL_REQUIRED;
import static com.p2p.condominium.constant.ErrorConstant.REQUEST_NAME_REQUIRED;
import static com.p2p.condominium.constant.StackholderConstant.ADDRESS;
import static com.p2p.condominium.constant.StackholderConstant.CNPJ_DESCRIPTION;
import static com.p2p.condominium.constant.StackholderConstant.CPF_DESCRIPTION;
import static com.p2p.condominium.constant.StackholderConstant.EMAIL;
import static com.p2p.condominium.constant.StackholderConstant.NAME;
import static com.p2p.condominium.constant.StackholderConstant.PHONES;
import static com.p2p.condominium.util.CpfCnpjUtil.removeSpecialCharacters;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StackHolderInsertRequest {
    @Schema(description = CPF_DESCRIPTION)
    @CPF(message = REQUEST_CPF_INVALIDO)
    private String cpf;

    @Schema(description = CNPJ_DESCRIPTION)
    @CNPJ(message = REQUEST_CNPJ_INVALIDO)
    private String cnpj;

    @Schema(description = NAME)
    @NotBlank(message = REQUEST_NAME_REQUIRED)
    private String name;

    @Schema(description = EMAIL)
    @NotBlank(message = REQUEST_EMAIL_REQUIRED)
    @Email(message = REQUEST_EMAIL_INVALID)
    private String email;

    @JsonIgnore
    private String identification;

    @Schema(description = PHONES)
    private List<Phone> phones;

    @Schema(description = ADDRESS)
    private Address address;

    public String getIdentification() {
        return this.getCpf() != null ? this.getCpf() : this.getCnpj();
    }

    public String getCpf() {
        return removeSpecialCharacters(cpf);
    }

    public String getCnpj() {
        return removeSpecialCharacters(cnpj);
    }

    public TypePersonEnum getTypePerson() {
        return this.cpf != null ? TypePersonEnum.FISICA : TypePersonEnum.JURIDICA;
    }


}
