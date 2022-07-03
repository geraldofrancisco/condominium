package com.p2p.condominium.service.impl;

import com.p2p.condominium.builder.PaginatorResponseBuider;
import com.p2p.condominium.builder.StackHolderBuilder;
import com.p2p.condominium.document.BasicInformationDocument;
import com.p2p.condominium.document.StackHolderDocument;
import com.p2p.condominium.dto.PaginatedResponse;
import com.p2p.condominium.dto.StackHolderInsertRequest;
import com.p2p.condominium.dto.StackHolderUpdateRequest;
import com.p2p.condominium.exception.BusinessException;
import com.p2p.condominium.repository.StackHolderRepository;
import com.p2p.condominium.service.StackHolderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static com.p2p.condominium.constant.ErrorConstant.STACKHOLDER_ID_NOT_EXIST;
import static com.p2p.condominium.constant.ErrorConstant.STACKHOLDER_LEGAL_PERSON_ID_NOT_EXIST;
import static com.p2p.condominium.constant.ErrorConstant.STACKHOLDER_PHYSICAL_PERSON_ID_NOT_EXIST;
import static com.p2p.condominium.enums.TypePersonEnum.FISICA;
import static com.p2p.condominium.enums.TypePersonEnum.JURIDICA;
import static com.p2p.condominium.util.BaseDocumentUtil.insertInformation;
import static com.p2p.condominium.util.BaseDocumentUtil.updateInformation;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@AllArgsConstructor
public class StackHolderServiceImpl implements StackHolderService {

    private StackHolderRepository repository;

    @Override
    public Mono<StackHolderDocument> insert(StackHolderInsertRequest dto) {
        var document = StackHolderBuilder.toDocument(dto);
        insertInformation(document);
        return this.repository.findByIdentification(dto.getIdentification())
                .switchIfEmpty(this.repository.save(document));
    }

    @Override
    public Mono<StackHolderDocument> update(StackHolderUpdateRequest dto) {
        var document = StackHolderBuilder.toDocument(dto);
        updateInformation(document);
        return findById(dto.getId())
                .flatMap(sh -> this.repository.save(document));
    }

    @Override
    public Mono<Void> delete(String id) {
        return findById(id)
                .flatMap(sh -> this.repository.delete(sh));
    }

    @Override
    public Mono<StackHolderDocument> findById(String id) {
        return this.repository.findById(id)
                .switchIfEmpty(Mono.error(new BusinessException(STACKHOLDER_ID_NOT_EXIST, NOT_FOUND)));
    }

    @Override
    public Mono<StackHolderDocument> findByPhysicalPersonAndId(String id) {
        return this.repository.findByTypePersonEnumAndId(FISICA, id)
                .switchIfEmpty(Mono.error(new BusinessException(STACKHOLDER_PHYSICAL_PERSON_ID_NOT_EXIST, NOT_FOUND)));
    }

    @Override
    public Mono<StackHolderDocument> findByLegalPersonAndId(String id) {
        return this.repository.findByTypePersonEnumAndId(JURIDICA, id)
                .switchIfEmpty(Mono.error(new BusinessException(STACKHOLDER_LEGAL_PERSON_ID_NOT_EXIST, NOT_FOUND)));
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
