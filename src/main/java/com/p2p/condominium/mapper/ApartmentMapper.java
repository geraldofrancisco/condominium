package com.p2p.condominium.mapper;

import com.p2p.condominium.document.ApartmentDocument;
import com.p2p.condominium.dto.ApartmentInsertRequest;
import com.p2p.condominium.dto.ApartmentResponse;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ApartmentMapper {
    ApartmentDocument insertToDocument(ApartmentInsertRequest dto);

    ApartmentResponse toResponse(ApartmentDocument document);

    List<ApartmentResponse> toResponse(List<ApartmentDocument> list);
}
