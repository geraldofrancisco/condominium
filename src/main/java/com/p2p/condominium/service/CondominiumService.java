package com.p2p.condominium.service;

import com.p2p.condominium.document.CondominiumDocument;
import com.p2p.condominium.dto.CondominiumDTO;
import com.p2p.condominium.dto.PaginatedResponse;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface CondominiumService {
    Mono<CondominiumDocument> insert(CondominiumDTO dto);
    Mono<CondominiumDocument> update(CondominiumDTO dto);
    Mono<Void> delete(String id);
    Mono<CondominiumDocument> findById(String id);
    Mono<PaginatedResponse> findAll(Pageable pageable);
}
