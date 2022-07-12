package com.p2p.condominium.repository;

import com.p2p.condominium.document.ApartmentDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ApartmentRepository extends ReactiveMongoRepository<ApartmentDocument, String> {
    Mono<Boolean> existsByBuilding(String building);
    Mono<Boolean> existsByOwner(String owner);
}
