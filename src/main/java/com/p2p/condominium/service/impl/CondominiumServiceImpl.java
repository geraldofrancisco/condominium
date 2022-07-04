package com.p2p.condominium.service.impl;

import com.p2p.condominium.builder.CondominiumBuilder;
import com.p2p.condominium.builder.PaginatorResponseBuider;
import com.p2p.condominium.document.CondominiumDocument;
import com.p2p.condominium.dto.CondominiumDTO;
import com.p2p.condominium.dto.PaginatedResponse;
import com.p2p.condominium.exception.BusinessException;
import com.p2p.condominium.repository.CondominiumRepository;
import com.p2p.condominium.service.CondominiumService;
import com.p2p.condominium.service.StackHolderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.p2p.condominium.constant.ErrorConstant.ID_NOT_EXIST;
import static com.p2p.condominium.util.BaseDocumentUtil.insertInformation;
import static com.p2p.condominium.util.BaseDocumentUtil.updateInformation;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@AllArgsConstructor
public class CondominiumServiceImpl implements CondominiumService {

    private CondominiumRepository repository;

    private StackHolderService stackHolderService;

    @Override
    public Mono<CondominiumDocument> insert(CondominiumDTO dto) {
        var document = CondominiumBuilder.toDocument(dto);
        insertInformation(document);
        return this.repository.findByIdentification(dto.getIdentification())
                .switchIfEmpty(saveDocument(document, dto.getConstructionCompanyId()));
    }

    private Mono<CondominiumDocument> saveDocument(CondominiumDocument document, String constructionId) {

        return stackHolderService.findByLegalPersonAndId(constructionId)
                .flatMap(sh -> {
                    document.setConstructionCompany(sh.getId());
                    return Mono.just(document);
                })
                .flatMap(repository::save);
    }

    @Override
    public Mono<CondominiumDocument> update(CondominiumDTO dto) {
        var document = CondominiumBuilder.toDocument(dto);
        updateInformation(document);
        return this.findById(dto.getId())
                .flatMap(c -> this.repository.save(document));
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