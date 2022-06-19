package com.p2p.condominium.builder;

import com.p2p.condominium.dto.PaginatorResponse;

import java.util.List;

public class PaginatorResponseBuider {
    public static PaginatorResponse toPaginator(List list, Integer page, Integer size, Long totalRecords) {
        return PaginatorResponse.builder()
                .list(list)
                .page(page)
                .size(size)
                .totalRecords(totalRecords)
                .build();
    }
}
