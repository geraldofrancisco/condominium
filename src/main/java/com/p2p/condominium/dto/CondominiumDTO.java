package com.p2p.condominium.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CondominiumDTO {
    private String id;
    private String name;
    private String constructionCompanyId;
    private String condominiumManagerId;
    private Address address;
}
