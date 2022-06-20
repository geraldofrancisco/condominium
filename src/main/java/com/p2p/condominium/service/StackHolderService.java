package com.p2p.condominium.service;

import com.p2p.condominium.document.StackHolderDocument;
import com.p2p.condominium.dto.PaginatedResponse;
import com.p2p.condominium.dto.StackHolderInsertRequest;
import com.p2p.condominium.dto.StackHolderUpdateRequest;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface StackHolderService {
    Mono<StackHolderDocument> insert(StackHolderInsertRequest dto);

    Mono<StackHolderDocument> update(StackHolderUpdateRequest dto);

    Mono<Void> delete(String id);

    Mono<StackHolderDocument> findById(String id);

    Mono<PaginatedResponse> findAll(Pageable pageable);
}
