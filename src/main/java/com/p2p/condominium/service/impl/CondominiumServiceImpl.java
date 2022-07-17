package com.p2p.condominium.service.impl;

import com.p2p.condominium.document.CondominiumDocument;
import com.p2p.condominium.dto.CondominiumDTO;
import com.p2p.condominium.dto.CondominiumResponse;
import com.p2p.condominium.exception.BusinessException;
import com.p2p.condominium.mapper.CondominiumMapper;
import com.p2p.condominium.repository.BuildingRepository;
import com.p2p.condominium.repository.CondominiumRepository;
import com.p2p.condominium.service.CondominiumService;
import com.p2p.condominium.service.StackHolderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.p2p.condominium.constant.ErrorConstant.CONDOMINIUM_EXISTS_BUILDING_NOT_DELETE;
import static com.p2p.condominium.constant.ErrorConstant.ID_NOT_EXIST;
import static com.p2p.condominium.util.BaseDocumentUtil.insertInformation;
import static com.p2p.condominium.util.BaseDocumentUtil.updateInformation;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@AllArgsConstructor
public class CondominiumServiceImpl implements CondominiumService {

    private CondominiumRepository repository;

    private StackHolderService stackHolderService;

    private CondominiumMapper mapper;

    private BuildingRepository buildingRepository;

    @Override
    public Mono<CondominiumDocument> insert(CondominiumDTO dto) {
        var document = mapper.toDocument(dto);
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
        var document = mapper.toDocument(dto);
        updateInformation(document);
        return this.findById(dto.getId())
                .flatMap(c -> saveDocument(document, dto.getConstructionCompanyId()));
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.findById(id)
                .flatMap(this::validatesIfYouCanDelete)
                .flatMap(c -> this.repository.delete(c));
    }

    @Override
    public Mono<CondominiumDocument> findById(String id) {
        return this.repository.findById(id)
                .switchIfEmpty(Mono.error(new BusinessException(ID_NOT_EXIST, NOT_FOUND)));
    }

    @Override
    public Mono<Page<CondominiumResponse>> findAll(Pageable pageable) {
        return this.repository.count().flatMap(total ->
                this.repository.findByIdNotNullOrderByNameAsc(pageable)
                        .collectList()
                        .map(list -> new PageImpl<>(this.mapper.toResponse(list), pageable, total))

        );
    }

    private Mono<CondominiumDocument> validatesIfYouCanDelete(CondominiumDocument document) {
        return this.buildingRepository.existsByCondominium(document.getId())
                .flatMap(exists -> {
                    if (exists)
                        return Mono.error(new BusinessException(CONDOMINIUM_EXISTS_BUILDING_NOT_DELETE));
                    return Mono.empty();
                }).thenReturn(document);
    }
}
