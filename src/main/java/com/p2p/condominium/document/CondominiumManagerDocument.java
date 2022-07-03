package com.p2p.condominium.document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@Builder
public class CondominiumManagerDocument {
    @Field("sindicoId")
    private String managerId;

    @Field("inicioVigencia")
    private LocalDate startValidity;

    @Field("fimVigencia")
    private LocalDate endValidity;
}
