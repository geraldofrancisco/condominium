package com.p2p.condominium.service.impl;

import com.p2p.condominium.builder.CondominiumBuilder;
import com.p2p.condominium.builder.PaginatorResponseBuider;
import com.p2p.condominium.document.CondominiumDocument;
import com.p2p.condominium.dto.CondominiumDTO;
import com.p2p.condominium.dto.PaginatedResponse;
import com.p2p.condominium.exception.BusinessException;
import com.p2p.condominium.repository.CondominiumRepository;
import com.p2p.condominium.service.CondominiumService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.p2p.condominium.constant.ErrorConstant.ID_NOT_EXIST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@AllArgsConstructor
public class CondominiuServiceImpl implements CondominiumService {

    private CondominiumRepository repository;

    @Override
    public Mono<CondominiumDocument> insert(CondominiumDTO dto) {
        return this.repository.findById(dto.getId())
                .switchIfEmpty(this.repository.save(CondominiumBuilder.toDocument(dto)));
    }

    @Override
    public Mono<CondominiumDocument> update(CondominiumDTO dto) {
        return this.findById(dto.getId())
                .flatMap(c -> this.repository.save(CondominiumBuilder.toDocument(dto)));
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.findById(id)
                .flatMap(c -> this.repository.delete(c));
    }

    @Override
    public Mono<CondominiumDocument> findById(String id) {
        return this.repository.findById(id)
                .switchIfEmpty(Mono.error(new BusinessException(ID_NOT_EXIST, NOT_FOUND)));
    }

    @Override
    public Mono<PaginatedResponse> findAll(Pageable pageable) {
        return this.repository.count().flatMap(total ->
                this.repository.findByIdNotNullOrderByNameAsc(pageable)
                        .collectList()
                        .flatMap(list -> Mono.just(PaginatorResponseBuider
                                .toPaginator(CondominiumBuilder.toDTO(list), pageable.getPageNumber(), pageable.getPageSize(), total)))
        );
    }
}
