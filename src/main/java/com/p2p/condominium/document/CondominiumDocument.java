package com.p2p.condominium.document;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "condominio")
@Builder
@ToString
@Data
public class CondominiumDocument {
    @Id
    private String id;

    @Field("nome")
    private String name;

    @Field("construtora")
    private StackHolderDocument constructionCompany;

    @Field("sindico")
    private StackHolderDocument condominiumManager;

    @Field("endereco")
    private AddressDocument address;
}
