package com.p2p.condominium.builder;

import com.p2p.condominium.document.AddressDocument;
import com.p2p.condominium.dto.Address;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class AddressBuilder {
    public static final AddressDocument toDocument(Address dto) {
        if (dto == null)
            return null;

        return AddressDocument.builder()
                .state(dto.getState())
                .city(dto.getCity())
                .complement(dto.getComplement())
                .district(dto.getDistrict())
                .number(dto.getNumber())
                .street(dto.getStreet())
                .zipCode(dto.getZipCode())
                .build();
    }

    public static final Address toDTO(AddressDocument document) {
        if (document == null)
            return null;

        return Address.builder()
                .state(document.getState())
                .city(document.getCity())
                .complement(document.getComplement())
                .district(document.getDistrict())
                .number(document.getNumber())
                .street(document.getStreet())
                .zipCode(document.getZipCode())
                .build();
    }
}
