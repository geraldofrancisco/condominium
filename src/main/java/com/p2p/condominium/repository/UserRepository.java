package com.p2p.condominium.repository;

import com.p2p.condominium.document.UserDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<UserDocument, String> {
    Mono<UserDocument> findByEmail(String email);
}
