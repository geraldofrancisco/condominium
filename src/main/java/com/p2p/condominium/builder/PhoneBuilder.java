package com.p2p.condominium.builder;

import com.p2p.condominium.document.PhoneDocument;
import com.p2p.condominium.dto.Phone;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class PhoneBuilder {
    public static final List<PhoneDocument> toDocument(List<Phone> list) {
        if(list == null)
            return null;

        return list.stream().map(PhoneBuilder::toDocument).collect(Collectors.toList());
    }

    private static final PhoneDocument toDocument(Phone dto) {
        if(dto == null)
            return null;

        return PhoneDocument.builder()
                .active(dto.getActive())
                .code(dto.getCode())
                .number(dto.getNumber())
                .build();
    }

    public static final List<Phone> toDTO(List<PhoneDocument> list) {
        if(list == null)
            return null;

        return list.stream().map(PhoneBuilder::toDTO).collect(Collectors.toList());
    }

    private static final Phone toDTO(PhoneDocument document) {
        if(document == null)
            return null;

        return Phone.builder()
                .active(document.getActive())
                .code(document.getCode())
                .number(document.getNumber())
                .build();
    }
}
