package com.p2p.condominium.document;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Builder
@Data
@ToString
@Document(collection = "predio")
public class BuildingDocument extends BaseDocument{
    @Id
    private String id;

    @Field("condominio")
    private String condominium;

    @Field("nome")
    private String name;

    @Field("quantidadeAndares")
    private Integer numberOfFloors;

    @Field("quantidadeApartamentosPorAndar")
    private Integer numberOfApartmentsPerFloor;

    @Field("quantidadeVagasGaragemApartamento")
    private Integer numberOfParkingApartment;

    @Field("temElevador")
    private boolean containsElevator;

    @Field("apartamentos")
    private List<String> apartments;
}
