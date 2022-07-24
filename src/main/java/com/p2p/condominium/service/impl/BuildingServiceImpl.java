package com.p2p.condominium.service.impl;

import com.p2p.condominium.document.BuildingDocument;
import com.p2p.condominium.dto.BuildingDTO;
import com.p2p.condominium.dto.BuildingResponse;
import com.p2p.condominium.exception.BusinessException;
import com.p2p.condominium.mapper.BuildingMapper;
import com.p2p.condominium.repository.ApartmentRepository;
import com.p2p.condominium.repository.BuildingRepository;
import com.p2p.condominium.service.BuildingService;
import com.p2p.condominium.service.CondominiumService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.p2p.condominium.constant.ErrorConstant.BUILDING_EXISTS_APARTMENTS_NOT_DELETE;
import static com.p2p.condominium.constant.ErrorConstant.BUILDING_ID_NOT_EXIST;
import static com.p2p.condominium.util.BaseDocumentUtil.insertInformation;
import static com.p2p.condominium.util.BaseDocumentUtil.updateInformation;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
@AllArgsConstructor
public class BuildingServiceImpl implements BuildingService {

    private BuildingRepository repository;

    private BuildingMapper mapper;


    private CondominiumService condominiumService;

    private ApartmentRepository apartmentRepository;

    @Override
    public Mono<BuildingDocument> insert(BuildingDTO request) {
        var document = this.mapper.toDocument(request);
        insertInformation(document);
        return this.condominiumService.findById(request.getCondominium())
                .flatMap(c -> this.repository.findByNameIgnoreCaseAndCondominium(request.getName(), request.getCondominium()))
                .switchIfEmpty(this.repository.save(document));
    }

    @Override
    public Mono<BuildingDocument> update(BuildingDTO request) {
        var document = this.mapper.toDocument(request);
        updateInformation(document);
        return this.findById(request.getId())
                .flatMap(d -> this.repository.save(document));
    }

    @Override
    public Mono<BuildingDocument> findById(String id) {
        return this.repository.findById(id)
                .switchIfEmpty(Mono.error(new BusinessException(BUILDING_ID_NOT_EXIST, NOT_FOUND)));
    }

    @Override
    public Mono<Page<BuildingResponse>> findAll(String condominium, Pageable pageable) {
        return this.repository.countByCondominium(condominium)
                .flatMap(total ->
                        this.repository.findByCondominiumOrderByName(condominium, pageable)
                                .collectList()
                                .map(list -> new PageImpl<>(this.mapper.toResponse(list), pageable, total))
                );
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.findById(id)
                .flatMap(this::validatesIfYouCanDelete)
                .flatMap(this.repository::delete);
    }

    private Mono<BuildingDocument> validatesIfYouCanDelete(BuildingDocument document) {
        return this.apartmentRepository.existsByBuilding(document.getId())
                .flatMap(exists -> {
                    if (exists)
                        return Mono.error(new BusinessException(BUILDING_EXISTS_APARTMENTS_NOT_DELETE));
                    return Mono.empty();
                }).thenReturn(document);
    }

}
