package com.p2p.condominium.builder;

import com.p2p.condominium.dto.PaginatedResponse;

import java.util.List;

public class PaginatorResponseBuider {
    public static PaginatedResponse toPaginator(List content, Integer page, Integer size, Long totalRecords) {
        return PaginatedResponse.builder()
                .content(content)
                .page(page)
                .size(size)
                .totalRecords(totalRecords)
                .build();
    }
}
