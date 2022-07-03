package com.p2p.condominium.document;

import com.p2p.condominium.enums.TypePersonEnum;
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
public class StackHolderDocument extends BaseDocument {
    @Id
    private String id;

    @Field("identificacao")
    private String identification;

    @Field("nome")
    private String name;

    private String email;

    @Field("tipoPessoa")
    private TypePersonEnum typePersonEnum;

    @Field("endereco")
    private AddressDocument address;

    @Field("telefones")
    private List<PhoneDocument> phones;
}
