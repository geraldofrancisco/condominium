package com.p2p.condominium.builder;

import com.p2p.condominium.document.StackHolderDocument;
import com.p2p.condominium.dto.StackHolderResponse;
import com.p2p.condominium.dto.StackHolderInsertRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StackHolderBuilder {
    public static final StackHolderDocument toDocument(StackHolderResponse dto) {
        return StackHolderDocument.builder()
                .email(dto.getEmail())
                .id(dto.getId())
                .name(dto.getName())
                .identification(dto.getIdentification())
                .phones(PhoneBuilder.toDocument(dto.getPhones()))
                .build();
    }

    public static final StackHolderDocument toDocument(StackHolderInsertRequest dto) {
        return StackHolderDocument.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .identification(dto.getIdentification())
                .phones(PhoneBuilder.toDocument(dto.getPhones()))
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
                .phones(PhoneBuilder.toDTO(document.getPhones()))
                .build();
    }
}
