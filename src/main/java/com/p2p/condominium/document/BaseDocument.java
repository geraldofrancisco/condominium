package com.p2p.condominium.document;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public abstract class BaseDocument {
    @Field("informacoesBasicas")
    private BasicInformationDocument basicInformation;

}
