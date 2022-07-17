package com.p2p.condominium.service;

import com.p2p.condominium.document.BuildingDocument;
import com.p2p.condominium.dto.BuildingDTO;
import com.p2p.condominium.dto.BuildingResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface BuildingService {
    Mono<BuildingDocument> insert(BuildingDTO request);

    Mono<BuildingDocument> update(BuildingDTO request);

    Mono<BuildingDocument> findById(String id);

    Mono<Page<BuildingResponse>> findAll(String condominium, Pageable pageable);

    Mono<Void> delete(String id);
}
