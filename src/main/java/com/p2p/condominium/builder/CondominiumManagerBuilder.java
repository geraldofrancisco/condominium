package com.p2p.condominium.builder;

import com.p2p.condominium.document.CondominiumManagerDocument;
import com.p2p.condominium.dto.CondominiumManagerResponse;

public class CondominiumManagerBuilder {
    public static CondominiumManagerResponse toResponse(final CondominiumManagerDocument document) {
        if(document == null)
            return null;

        return CondominiumManagerResponse.builder()
                .managerId(document.getManagerId())
                .startValidity(document.getStartValidity())
                .endValidity(document.getEndValidity())
                .build();
    }
}
