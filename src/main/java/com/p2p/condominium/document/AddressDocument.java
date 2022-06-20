package com.p2p.condominium.document;

import com.p2p.condominium.enums.StateEnum;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
public class AddressDocument {
    @Field("cep")
    private String zipCode;

    @Field("rua")
    private String street;

    @Field("numero")
    private Integer number;

    @Field("complemento")
    private String complement;

    @Field("bairro")
    private String district;

    @Field("cidade")
    private String city;

    @Field("estado")
    private StateEnum state;
}
