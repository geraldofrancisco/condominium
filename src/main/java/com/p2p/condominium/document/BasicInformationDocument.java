package com.p2p.condominium.document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Builder
public class BasicInformationDocument {
    @Field("dataInsercao")
    private LocalDateTime insertionDate;

    @Field("dataUltimaAtualizacao")
    private LocalDateTime lastUpdateDate;

    @Field("dataExclusao")
    private LocalDateTime active;
}
