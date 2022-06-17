package com.p2p.condominium.repository;

import com.p2p.condominium.document.StackHolderDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface StackHolderRepository extends ReactiveMongoRepository<StackHolderDocument, String> {
}
