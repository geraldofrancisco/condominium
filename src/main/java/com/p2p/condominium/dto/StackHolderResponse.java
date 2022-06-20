package com.p2p.condominium.dto;

import com.p2p.condominium.enums.TypePerson;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class StackHolderResponse {
    private String id;
    private String identification;
    private String name;
    private String email;
    private List<Phone> phones;
    private TypePerson typePerson;
}
