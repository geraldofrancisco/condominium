package com.p2p.condominium.service.impl;

import com.p2p.condominium.builder.StackHolderBuilder;
import com.p2p.condominium.document.StackHolderDocument;
import com.p2p.condominium.dto.StackHolderDTO;
import com.p2p.condominium.repository.StackHolderRepository;
import com.p2p.condominium.service.StackHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StackHolderServiceImpl implements StackHolderService {

    @Autowired
    private StackHolderRepository repository;

    @Override
    public Mono<StackHolderDocument> insert(StackHolderDTO dto) {
        var entity = StackHolderBuilder.toDocument(dto);
        return this.repository.save(entity);
    }

    @Override
    public Mono<StackHolderDocument> findById(String id) {
        return Mono.empty();
    }

    @Override
    public Flux<StackHolderDocument> findAll(Pageable pageable) {
        //return Mono.just(this.repository.findAll(pageable))
          //      .map(p -> StackHolderBuilder.toDTO(p, pageable));
        return this.repository.findByIdNotNullOrderByNameAsc(pageable);
    }
}
