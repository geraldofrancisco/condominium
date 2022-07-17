package com.p2p.condominium.service.impl;

import com.p2p.condominium.document.StackHolderDocument;
import com.p2p.condominium.dto.PaginatedResponse;
import com.p2p.condominium.dto.StackHolderInsertRequest;
import com.p2p.condominium.dto.StackHolderUpdateRequest;
import com.p2p.condominium.exception.BusinessException;
import com.p2p.condominium.mapper.PaginatedResponseMapper;
import com.p2p.condominium.mapper.StackHolderMapper;
import com.p2p.condominium.repository.ApartmentRepository;
import com.p2p.condominium.repository.CondominiumRepository;
import com.p2p.condominium.repository.StackHolderRepository;
import com.p2p.condominium.service.StackHolderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import static com.p2p.condominium.constant.ErrorConstant.APARTMENT_DONT_INSERT;
import static com.p2p.condominium.constant.ErrorConstant.STACKHOLDER_ID_NOT_EXIST;
import static com.p2p.condominium.constant.ErrorConstant.STACKHOLDER_IN_USE_APARTMENT;
import static com.p2p.condominium.constant.ErrorConstant.STACKHOLDER_IN_USE_CONDOMINIUM;
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

    private PaginatedResponseMapper paginatedResponseMapper;

    private StackHolderMapper stackHolderMapper;

    private CondominiumRepository condominiumRepository;

    private ApartmentRepository apartmentRepository;

    @Override
    public Mono<StackHolderDocument> insert(StackHolderInsertRequest dto) {
        var document = stackHolderMapper.toDocument(dto);
        insertInformation(document);
        return this.repository.findByIdentification(dto.getIdentification())
                .switchIfEmpty(this.repository.save(document));
    }

    @Override
    public Mono<StackHolderDocument> update(StackHolderUpdateRequest dto) {
        var document = stackHolderMapper.toDocument(dto);
        updateInformation(document);
        return findById(dto.getId())
                .flatMap(sh -> this.repository.save(document));
    }

    @Transactional
    @Override
    public Mono<Void> delete(String id) {
        return findById(id)
                .flatMap(this::validateDeleteCondominium)
                .flatMap(this::validateDeleteApartment)
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
                        .flatMap(list -> Mono.just(paginatedResponseMapper
                                .toPaginator(stackHolderMapper.toResponse(list), pageable.getPageNumber(), pageable.getPageSize(), total)))
        );

    }


    private Mono<StackHolderDocument> validateDeleteCondominium(StackHolderDocument document) {
        return condominiumRepository.existsByConstructionCompanyOrCondominiumManagerManagerId(document.getId(), document.getId())
                .flatMap(exists -> {
                    if (!exists)
                        return Mono.empty();
                    return Mono.error(new BusinessException(STACKHOLDER_IN_USE_CONDOMINIUM));
                }).thenReturn(document);
    }

    private Mono<StackHolderDocument> validateDeleteApartment(StackHolderDocument document) {
        return this.apartmentRepository.existsByOwner(document.getId())
                .flatMap(exists -> {
                    if (!exists)
                        return Mono.empty();
                    return Mono.error(new BusinessException(STACKHOLDER_IN_USE_APARTMENT));
                }).thenReturn(document);
    }
}
