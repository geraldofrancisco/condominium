package com.p2p.condominium.service.impl;

import com.p2p.condominium.document.BuildingDocument;
import com.p2p.condominium.dto.BuildingDTO;
import com.p2p.condominium.dto.PaginatedResponse;
import com.p2p.condominium.mapper.BuildingMapper;
import com.p2p.condominium.mapper.PaginatedResponseMapper;
import com.p2p.condominium.repository.BuildingRepository;
import com.p2p.condominium.service.BuildingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class BuildingServiceImpl implements BuildingService {

    private BuildingRepository repository;

    private BuildingMapper mapper;

    private PaginatedResponseMapper paginatedResponseMapper;

    @Override
    public Mono<BuildingDocument> insert(BuildingDTO request) {
        return null;
    }

    @Override
    public Mono<BuildingDocument> update(BuildingDTO request) {
        return null;
    }

    @Override
    public Mono<BuildingDocument> findById(String id) {
        return null;
    }

    @Override
    public Mono<PaginatedResponse> findAll(Pageable pageable, String condominium) {
        return this.repository.countByCondominium(condominium)
                .flatMap(total ->
                        this.repository.findByCondominiumOrderByName(condominium, pageable)
                                .collectList()
                                .flatMap(list -> Mono.just(this.paginatedResponseMapper
                                        .toPaginator(this.mapper.toResponse(list), pageable.getPageNumber(), pageable.getPageSize(), total)))
                );
    }

}
