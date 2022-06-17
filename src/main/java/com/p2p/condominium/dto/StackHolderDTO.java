package com.p2p.condominium.dto;

import com.p2p.condominium.document.PhoneDocument;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class StackHolderDTO {
    private String id;
    private String identification;
    private String name;
    private String email;
    private List<PhoneDTO> phones;
}
