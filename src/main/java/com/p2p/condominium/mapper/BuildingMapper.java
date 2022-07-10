package com.p2p.condominium.mapper;

import com.p2p.condominium.document.BuildingDocument;
import com.p2p.condominium.dto.BuildingDTO;
import com.p2p.condominium.dto.BuildingResponse;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface BuildingMapper {
    BuildingDocument toDocument(BuildingDTO dto);
    List<BuildingDocument> toDocument(List<BuildingDTO> list);

    BuildingDTO toDTO(BuildingDocument document);

    List<BuildingDTO> toDTO(List<BuildingDocument> list);

    BuildingResponse toResponse(BuildingDocument document);
    List<BuildingResponse> toResponse(List<BuildingDocument> list);
}
