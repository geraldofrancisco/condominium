package com.p2p.condominium.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PaginatedResponse {
    List content;
    Integer page;
    Integer size;
    Long totalRecords;

    public Long getNumberOfPages() {
        var last = totalRecords / size;
        return last > 0 ? last - 1L : 0;
    }

    public Boolean isLast() {
        return page >= getNumberOfPages();
    }
}
