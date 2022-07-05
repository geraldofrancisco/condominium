package com.p2p.condominium.mapper;

import com.p2p.condominium.document.CondominiumDocument;
import com.p2p.condominium.dto.CondominiumDTO;
import com.p2p.condominium.dto.CondominiumResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, CondominiumManagerMapper.class})
public interface CondominiumMapper {
    List<CondominiumDocument> toDocument(List<CondominiumDTO> list);

    CondominiumDocument toDocument(CondominiumDTO dto);

    List<CondominiumResponse> toDTO(List<CondominiumDocument> list);

    @Mapping(source = "document.constructionCompany", target = "constructionCompanyId")
    CondominiumResponse toResponse(CondominiumDocument document);
}
