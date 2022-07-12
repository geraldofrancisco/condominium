package com.p2p.condominium.service;

import com.p2p.condominium.document.BuildingDocument;
import com.p2p.condominium.dto.BuildingDTO;
import com.p2p.condominium.dto.PaginatedResponse;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface BuildingService {
    Mono<BuildingDocument> insert(BuildingDTO request);

    Mono<BuildingDocument> update(BuildingDTO request);

    Mono<BuildingDocument> findById(String id);

    Mono<PaginatedResponse> findAll(Pageable pageable, String condominium);

    Mono<Void> delete(String id);
}
