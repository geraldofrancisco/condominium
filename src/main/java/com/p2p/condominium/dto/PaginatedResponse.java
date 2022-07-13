package com.p2p.condominium.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

import static com.p2p.condominium.constant.PaginatedResponseConstant.LAST;
import static com.p2p.condominium.constant.PaginatedResponseConstant.LIST;
import static com.p2p.condominium.constant.PaginatedResponseConstant.MAX_PAGE;
import static com.p2p.condominium.constant.PaginatedResponseConstant.PAGE;
import static com.p2p.condominium.constant.PaginatedResponseConstant.SIZE;
import static com.p2p.condominium.constant.PaginatedResponseConstant.TOTAL_RECORDS;

@Data
@Builder
public class PaginatedResponse {
    @Schema(description = LIST)
    List content;
    @Schema(description = PAGE)
    Integer page;
    @Schema(description = SIZE)
    Integer size;
    @Schema(description = TOTAL_RECORDS)
    Long totalRecords;

    @Schema(description = MAX_PAGE)
    public Long getMaxPage() {
        var last = totalRecords / size;
        return last > 0 ? last - 1L : 0;
    }

    @Schema(description = LAST)
    public Boolean isLast() {
        return page >= getMaxPage();
    }
}
