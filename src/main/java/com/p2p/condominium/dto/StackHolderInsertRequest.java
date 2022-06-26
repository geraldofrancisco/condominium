package com.p2p.condominium.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.p2p.condominium.enums.TypePersonEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StackHolderInsertRequest {
    @CPF(message = REQUEST_CPF_INVALIDO)
    private String cpf;

    @CNPJ(message = REQUEST_CNPJ_INVALIDO)
    private String cnpj;

    @NotBlank(message = REQUEST_NAME_REQUIRED)
    private String name;

    @NotBlank(message = REQUEST_EMAIL_REQUIRED)
    @Email(message = REQUEST_EMAIL_INVALID)
    private String email;

    @JsonIgnore
    private String identification;

    private List<Phone> phones;

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

    private String removeSpecialCharacters(String string) {
        if(StringUtils.isBlank(string))
            return null;

        return string
                .replace(".","")
                .replace("-", "")
                .replace("/", "");
    }
}
