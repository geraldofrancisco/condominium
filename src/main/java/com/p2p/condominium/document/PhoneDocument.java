package com.p2p.condominium.document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
public class PhoneDocument {
    @Field("ddd")
    private Integer code;

    @Field("numero")
    private Integer number;

    @Field("ativo")
    private Boolean active;
}
