package com.p2p.condominium.builder;

import com.p2p.condominium.document.CondominiumDocument;
import com.p2p.condominium.document.StackHolderDocument;
import com.p2p.condominium.dto.CondominiumDTO;
import com.p2p.condominium.dto.CondominiumResponse;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

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
                .address(AddressBuilder.toDocument(dto.getAddress()))
                .build();
    }

    public static StackHolderDocument toStackHoldDocumentId(String id) {
        if(StringUtils.isBlank(id))
            return null;

        return StackHolderDocument.builder()
                .id(id).build();
    }

    public static List<CondominiumResponse> toDTO(List<CondominiumDocument> list) {
        return list.stream().map(CondominiumBuilder::toResponse).collect(Collectors.toList());
    }

    public static CondominiumResponse toResponse(CondominiumDocument document) {
        return CondominiumResponse.builder()
                .id(document.getId())
                .name(document.getName())
                .condominiumManager(CondominiumManagerBuilder.toResponse(document.getCondominiumManager()))
                .constructionCompanyId(document.getConstructionCompany())
                .address(AddressBuilder.toResponse(document.getAddress()))
                .build();
    }
}
