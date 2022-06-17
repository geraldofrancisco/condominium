package com.p2p.condominium.builder;

import com.p2p.condominium.document.StackHolderDocument;
import com.p2p.condominium.dto.StackHolderDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StackHolderBuilder {
    public static final StackHolderDocument toDocument(StackHolderDTO dto) {
        return StackHolderDocument.builder()
                .email(dto.getEmail())
                .id(dto.getId())
                .name(dto.getName())
                .identification(dto.getIdentification())
                .phones(PhoneBuilder.toDocument(dto.getPhones()))
                .build();
    }


    public static final StackHolderDTO toDTO(StackHolderDocument document) {
        return StackHolderDTO.builder()
                .id(document.getId())
                .email(document.getEmail())
                .name(document.getName())
                .identification(document.getIdentification())
                .phones(PhoneBuilder.toDTO(document.getPhones()))
                .build();
    }
}
