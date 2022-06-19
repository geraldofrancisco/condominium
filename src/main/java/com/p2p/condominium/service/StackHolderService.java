package com.p2p.condominium.service;

import com.p2p.condominium.document.StackHolderDocument;
import com.p2p.condominium.dto.PaginatorResponse;
import com.p2p.condominium.dto.StackHolderDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface StackHolderService {
    Mono<StackHolderDocument> insert(StackHolderDTO dto);
    Mono<StackHolderDocument> findById(String id);

    Mono<PaginatorResponse> findAll(Pageable pageable);
}
