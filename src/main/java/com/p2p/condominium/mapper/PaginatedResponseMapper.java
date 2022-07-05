package com.p2p.condominium.mapper;

import com.p2p.condominium.dto.PaginatedResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaginatedResponseMapper {
    PaginatedResponse toPaginator(List content, Integer page, Integer size, Long totalRecords);
}
