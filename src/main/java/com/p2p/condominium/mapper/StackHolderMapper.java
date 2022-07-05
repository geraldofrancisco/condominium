package com.p2p.condominium.mapper;

import com.p2p.condominium.document.StackHolderDocument;
import com.p2p.condominium.dto.StackHolderInsertRequest;
import com.p2p.condominium.dto.StackHolderResponse;
import com.p2p.condominium.dto.StackHolderUpdateRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, PhoneMapper.class})
public interface StackHolderMapper {
    StackHolderDocument toDocument(StackHolderInsertRequest dto);

    StackHolderDocument toDocument(StackHolderUpdateRequest dto);

    List<StackHolderResponse> toResponse(List<StackHolderDocument> list);

    StackHolderResponse toResponse(StackHolderDocument document);
}
