package com.p2p.condominium.service;

import com.p2p.condominium.document.ApartmentDocument;
import com.p2p.condominium.dto.ApartmentInsertRequest;
import com.p2p.condominium.dto.ApartmentOwnerRequest;
import com.p2p.condominium.dto.ApartmentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface ApartmentService {
    Mono<Page<ApartmentResponse>> findAll(String builder, Pageable pageable);

    Mono<ApartmentDocument> findById(String id);

    Mono<ApartmentDocument> insert(ApartmentInsertRequest request);

    Mono<Void> delete(String id);

    Mono<ApartmentDocument> assignApartmentOwner(ApartmentOwnerRequest request);
}
