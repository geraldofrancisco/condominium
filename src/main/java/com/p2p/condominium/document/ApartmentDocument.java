package com.p2p.condominium.document;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Builder
@Data
@ToString
@Document(collection = "apartamento")
public class ApartmentDocument {
    @Id
    private String id;

    @Field("predio")
    private String building;

    @Field("proprietario")
    private String owner;

    @Field("andar")
    private Integer floor;

    @Field("numero")
    private Integer number;
}
