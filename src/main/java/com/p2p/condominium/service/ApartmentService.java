package com.p2p.condominium.service;

import com.p2p.condominium.document.ApartmentDocument;
import com.p2p.condominium.dto.ApartmentInsertRequest;
import com.p2p.condominium.dto.ApartmentUpdateRequest;
import com.p2p.condominium.dto.PaginatedResponse;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface ApartmentService {
    Mono<PaginatedResponse> findAll(String builder, Pageable pageable);
    Mono<ApartmentDocument> findById(String id);
    Mono<ApartmentDocument> insert(ApartmentInsertRequest request);

    Mono<ApartmentDocument> update(ApartmentUpdateRequest request);

    Mono<ApartmentDocument> assignApartmentOwner(String apartment, String owner);
}
