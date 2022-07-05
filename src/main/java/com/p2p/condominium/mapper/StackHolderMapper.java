package com.p2p.condominium.mapper;

import com.p2p.condominium.document.StackHolderDocument;
import com.p2p.condominium.dto.StackHolderInsertRequest;
import com.p2p.condominium.dto.StackHolderResponse;
import com.p2p.condominium.dto.StackHolderUpdateRequest;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, PhoneMapper.class})
public interface StackHolderMapper {
    @Mapping(source = "dto.typePerson", target = "typePersonEnum")
    StackHolderDocument toDocument(StackHolderInsertRequest dto);

    @Mapping(source = "dto.typePerson", target = "typePersonEnum")
    StackHolderDocument toDocument(StackHolderUpdateRequest dto);

    List<StackHolderResponse> toResponse(List<StackHolderDocument> list);

    StackHolderResponse toResponse(StackHolderDocument document);
}
