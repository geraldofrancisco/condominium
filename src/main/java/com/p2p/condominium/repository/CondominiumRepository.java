package com.p2p.condominium.repository;

import com.p2p.condominium.document.CondominiumDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CondominiumRepository extends ReactiveMongoRepository<CondominiumDocument, String> {
    Flux<CondominiumDocument> findByIdNotNullOrderByNameAsc(final Pageable page);
}
