package com.p2p.condominium.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PaginatorResponse {
    List list;
    Integer page;
    Integer size;
    Long totalRecords;
}
