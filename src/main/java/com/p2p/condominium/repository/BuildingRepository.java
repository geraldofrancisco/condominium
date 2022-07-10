package com.p2p.condominium.repository;

import com.p2p.condominium.document.BuildingDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends ReactiveMongoRepository<BuildingDocument, String> {
}
