package com.p2p.condominium.builder;

import com.p2p.condominium.dto.PaginatedResponse;
import lombok.NoArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
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
