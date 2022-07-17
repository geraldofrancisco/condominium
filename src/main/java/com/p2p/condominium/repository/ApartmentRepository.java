package com.p2p.condominium.repository;

import com.p2p.condominium.document.ApartmentDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ApartmentRepository extends ReactiveMongoRepository<ApartmentDocument, String> {
    Mono<Boolean> existsByBuilding(String building);
    Mono<Boolean> existsByOwner(String owner);

    Mono<Boolean> existsByBuildingAndFloorAndNumber(String building, Integer floor, Integer number);

    Mono<Long> countByBuildingAndFloor(String building, Integer floor);
    Mono<Long> countByBuilding(String building);

    Flux<ApartmentDocument> findByBuildingOrderByFloorAscNumberAsc(String building, Pageable pageable);
}
