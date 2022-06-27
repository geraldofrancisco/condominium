package com.p2p.condominium.builder;

import com.p2p.condominium.document.CondominiumDocument;
import com.p2p.condominium.document.StackHolderDocument;
import com.p2p.condominium.dto.CondominiumDTO;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CondominiumBuilder {
    public static List<CondominiumDocument> toDocument(List<CondominiumDTO> list) {
        return list.stream().map(CondominiumBuilder::toDocument).collect(Collectors.toList());
    }

    public static CondominiumDocument toDocument(CondominiumDTO dto) {
        return CondominiumDocument.builder()
                .id(dto.getId())
                .name(dto.getName())
                .condominiumManager(StackHolderDocument.builder().id(dto.getCondominiumManagerId()).build())
                .constructionCompany(StackHolderDocument.builder().id(dto.getConstructionCompanyId()).build())
                .address(AddressBuilder.toDocument(dto.getAddress()))
                .build();
    }

    public static List<CondominiumDTO> toDTO(List<CondominiumDocument> list) {
        return list.stream().map(CondominiumBuilder::toDTO).collect(Collectors.toList());
    }

    public static CondominiumDTO toDTO(CondominiumDocument document) {
        return CondominiumDTO.builder()
                .id(document.getId())
                .name(document.getName())
                .condominiumManagerId(document.getCondominiumManager().getId())
                .constructionCompanyId(document.getConstructionCompany().getId())
                .address(AddressBuilder.toDTO(document.getAddress()))
                .build();
    }
}
