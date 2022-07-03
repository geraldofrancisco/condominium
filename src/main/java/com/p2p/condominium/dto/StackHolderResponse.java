package com.p2p.condominium.dto;

import com.p2p.condominium.enums.TypePersonEnum;
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
    private TypePersonEnum typePersonEnum;
    private AddressResponse address;
}
