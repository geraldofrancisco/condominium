package com.p2p.condominium.mapper;

import com.p2p.condominium.document.PhoneDocument;
import com.p2p.condominium.dto.Phone;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PhoneMapper {
    List<PhoneDocument> toDocument(List<Phone> phones);

    PhoneDocument toDocument(Phone dto);

    List<Phone> toDTO(List<PhoneDocument> phones);

    Phone toDTO(PhoneDocument document);
}
