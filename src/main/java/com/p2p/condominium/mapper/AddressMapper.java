package com.p2p.condominium.mapper;

import com.p2p.condominium.document.AddressDocument;
import com.p2p.condominium.dto.Address;
import com.p2p.condominium.dto.AddressResponse;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface AddressMapper {
    AddressDocument toDocument(Address address);
    AddressResponse toResponse(AddressDocument document);
}
