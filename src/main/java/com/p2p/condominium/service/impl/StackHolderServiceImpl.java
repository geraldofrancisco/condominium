package com.p2p.condominium.service.impl;

import com.p2p.condominium.builder.PaginatorResponseBuider;
import com.p2p.condominium.builder.StackHolderBuilder;
import com.p2p.condominium.document.StackHolderDocument;
import com.p2p.condominium.dto.PaginatedResponse;
import com.p2p.condominium.dto.StackHolderInsertRequest;
import com.p2p.condominium.exception.BusinessException;
import com.p2p.condominium.repository.StackHolderRepository;
import com.p2p.condominium.service.StackHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.p2p.condominium.constant.ErrorConstant.ID_NOT_EXIST;

@Service
public class StackHolderServiceImpl implements StackHolderService {

    @Autowired
    private StackHolderRepository repository;

    @Override
    public Mono<StackHolderDocument> insert(StackHolderInsertRequest dto) {
        return this.repository.findByIdentification(dto.getIdentification())
                .switchIfEmpty(this.repository.save(StackHolderBuilder.toDocument(dto)));
    }

    @Override
    public Mono<StackHolderDocument> findById(String id) {
        return this.repository.findById(id)
                .switchIfEmpty(Mono.error(new BusinessException(ID_NOT_EXIST)));
    }

    @Override
    public Mono<PaginatedResponse> findAll(Pageable pageable) {
        return this.repository.count().flatMap(total ->
            this.repository.findByIdNotNullOrderByNameAsc(pageable)
                    .collectList()
                    .flatMap(list -> Mono.just(PaginatorResponseBuider
                            .toPaginator(StackHolderBuilder.toResponse(list), pageable.getPageNumber(), pageable.getPageSize(), total)))
        );

    }
}
