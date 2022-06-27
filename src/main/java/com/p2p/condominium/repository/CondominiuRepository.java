package com.p2p.condominium.repository;

import com.p2p.condominium.document.CondominiumDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CondominiuRepository extends ReactiveMongoRepository<CondominiumDocument, String> {
}
