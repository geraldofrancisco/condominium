package com.p2p.condominium.repository;

import com.p2p.condominium.document.StackHolderDocument;
import com.p2p.condominium.enums.TypePersonEnum;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StackHolderRepository extends ReactiveMongoRepository<StackHolderDocument, String> {
    Flux<StackHolderDocument> findByIdNotNullOrderByNameAsc(final Pageable page);
    Mono<StackHolderDocument> findByIdentification(final String identification);
    Mono<StackHolderDocument> findByTypePersonEnumAndId(final TypePersonEnum typePerson, String id);
}
