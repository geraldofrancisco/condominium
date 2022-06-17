package com.p2p.condominium.repository;

import com.p2p.condominium.document.StackHolderDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface StackHolderRepository extends ReactiveMongoRepository<StackHolderDocument, String> {
    Flux<StackHolderDocument> findByIdNotNullOrderByNameAsc(final Pageable page);
}
