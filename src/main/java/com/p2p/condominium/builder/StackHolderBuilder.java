package com.p2p.condominium.builder;

import com.p2p.condominium.document.StackHolderDocument;
import com.p2p.condominium.dto.StackHolderInsertRequest;
import com.p2p.condominium.dto.StackHolderResponse;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class StackHolderBuilder {
    public static final StackHolderDocument toDocument(StackHolderInsertRequest dto) {
        return StackHolderDocument.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .identification(dto.getIdentification())
                .typePersonEnum(dto.getTypePerson())
                .phones(PhoneBuilder.toDocument(dto.getPhones()))
                .address(AddressBuilder.toDocument(dto.getAddress()))
                .build();
    }



    public static final List<StackHolderResponse> toResponse(List<StackHolderDocument> list) {
        return list.stream().map(StackHolderBuilder::toResponse).collect(Collectors.toList());
    }

    public static final StackHolderResponse toResponse(StackHolderDocument document) {
        return StackHolderResponse.builder()
                .id(document.getId())
                .email(document.getEmail())
                .name(document.getName())
                .identification(document.getIdentification())
                .typePersonEnum(document.getTypePersonEnum())
                .phones(PhoneBuilder.toDTO(document.getPhones()))
                .address(AddressBuilder.toDTO(document.getAddress()))
                .build();
    }
}
