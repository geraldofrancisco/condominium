package com.p2p.condominium.mapper;

import com.p2p.condominium.dto.PaginatedResponse;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface PaginatedResponseMapper {
    PaginatedResponse toPaginator(List content, Integer page, Integer size, Long totalRecords);
}
