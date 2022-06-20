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

import javax.validation.constraints.NotBlank;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StackHolderUpdateRequest {

    @NotBlank
    private String id;
    @CPF
    private String cpf;

    @CNPJ
    private String cnpj;

    @NotBlank
    private String name;

    @NotBlank
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
