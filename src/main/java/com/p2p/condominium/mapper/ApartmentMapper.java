package com.p2p.condominium.mapper;

import com.p2p.condominium.document.ApartmentDocument;
import com.p2p.condominium.dto.ApartmentDTO;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ApartmentMapper {
    ApartmentDocument toDocument(ApartmentDTO dto);

    List<ApartmentDocument> toDocument(List<ApartmentDTO> list);

    ApartmentDTO toDTO(ApartmentDocument document);

    List<ApartmentDTO> toDTO(List<ApartmentDocument> list);
}
