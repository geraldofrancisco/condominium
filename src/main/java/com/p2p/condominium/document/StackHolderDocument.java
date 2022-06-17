package com.p2p.condominium.document;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "stackholder")
@Builder
@ToString
@Data
public class StackHolderDocument {
    @Id
    private String id;

    @Field(name = "identificacao")
    private String identification;

    @Field(name = "nome")
    private String name;

    private String email;

    @Field(name = "telefones")
    private List<PhoneDocument> phones;
}
