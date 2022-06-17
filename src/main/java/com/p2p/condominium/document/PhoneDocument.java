package com.p2p.condominium.document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
public class PhoneDocument {
    @Field(name = "ddd")
    private Integer code;

    @Field(name = "numero")
    private Integer number;

    @Field(name = "ativo")
    private Boolean active;
}
