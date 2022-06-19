package com.p2p.condominium.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StackHolderInsertRequest {
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

    private List<PhoneDTO> phones;

    public String getIdentification() {
        return this.getCpf() != null ? this.getCpf() : this.getCnpj();
    }

    public String getCpf() {
        return removeSpecialCharacters(cpf);
    }

    public String getCnpj() {
        return removeSpecialCharacters(cnpj);
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
