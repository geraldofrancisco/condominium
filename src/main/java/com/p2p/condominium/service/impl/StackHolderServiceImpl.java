package com.p2p.condominium.service.impl;

import com.p2p.condominium.builder.StackHolderBuilder;
import com.p2p.condominium.document.StackHolderDocument;
import com.p2p.condominium.dto.StackHolderDTO;
import com.p2p.condominium.repository.StackHolderRepository;
import com.p2p.condominium.service.StackHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class StackHolderServiceImpl implements StackHolderService {

    @Autowired
    StackHolderRepository repository;

    @Override
    public Mono<StackHolderDocument> insert(StackHolderDTO dto) {
        var entity = StackHolderBuilder.toDocument(dto);
        return repository.save(entity);
    }

    @Override
    public Mono<StackHolderDocument> findById(String id) {
        return Mono.empty();
    }
}
