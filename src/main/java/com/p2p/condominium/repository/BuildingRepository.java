package com.p2p.condominium.repository;

import com.p2p.condominium.document.BuildingDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BuildingRepository extends ReactiveMongoRepository<BuildingDocument, String> {
    Mono<Long> countByCondominium(String condominium);

    Mono<BuildingDocument> findByNameIgnoreCaseAndCondominium(String name, String condominium);

    Flux<BuildingDocument> findByCondominiumOrderByName(String condominium, Pageable pageable);

    Mono<Boolean> existsByCondominium(String condominium);

    Flux<String> findIdByCondominium(String condominium);
}
