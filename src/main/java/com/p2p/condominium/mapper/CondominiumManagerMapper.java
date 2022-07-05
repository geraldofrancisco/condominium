package com.p2p.condominium.mapper;

import com.p2p.condominium.document.CondominiumManagerDocument;
import com.p2p.condominium.dto.CondominiumManagerResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CondominiumManagerMapper {
    CondominiumManagerResponse toResponse(CondominiumManagerDocument document);
}
