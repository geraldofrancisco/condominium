package com.p2p.condominium.dto;

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
    private List<PhoneDTO> phones;
}
