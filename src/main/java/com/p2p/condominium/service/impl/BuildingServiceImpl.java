package com.p2p.condominium.service.impl;

import com.p2p.condominium.document.BuildingDocument;
import com.p2p.condominium.dto.BuildingDTO;
import com.p2p.condominium.dto.PaginatedResponse;
import com.p2p.condominium.exception.BusinessException;
import com.p2p.condominium.mapper.BuildingMapper;
import com.p2p.condominium.mapper.PaginatedResponseMapper;
import com.p2p.condominium.repository.BuildingRepository;
import com.p2p.condominium.service.ApartmentService;
import com.p2p.condominium.service.BuildingService;
import com.p2p.condominium.service.CondominiumService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.p2p.condominium.constant.ErrorConstant.BUILDING_ID_NOT_EXIST;
import static com.p2p.condominium.util.BaseDocumentUtil.insertInformation;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
@AllArgsConstructor
public class BuildingServiceImpl implements BuildingService {

    private BuildingRepository repository;

    private BuildingMapper mapper;

    private PaginatedResponseMapper paginatedResponseMapper;

    private CondominiumService condominiumService;

    private ApartmentService apartmentService;

    @Override
    public Mono<BuildingDocument> insert(BuildingDTO request) {
        var document = this.mapper.toDocument(request);
        insertInformation(document);
        return this.condominiumService.findById(request.getCondominium())
                .flatMap(c -> this.repository.findByNameIgnoreCaseAndCondominium(request.getName(), request.getCondominium()))
                .switchIfEmpty(this.createBuildingAndApartments(document));
    }

    private Mono<BuildingDocument> createBuildingAndApartments(BuildingDocument document) {
        return this.repository.save(document)
                .flatMap(building -> {
                    this.apartmentService.createApartaments(building);
                    return Mono.just(building);
                });
    }

    @Override
    public Mono<BuildingDocument> update(BuildingDTO request) {
        return null;
    }

    @Override
    public Mono<BuildingDocument> findById(String id) {
        return this.repository.findById(id)
                .switchIfEmpty(Mono.error(new BusinessException(BUILDING_ID_NOT_EXIST, NOT_FOUND)));
    }

    @Override
    public Mono<PaginatedResponse> findAll(Pageable pageable, String condominium) {
        return this.repository.countByCondominium(condominium)
                .flatMap(total ->
                        this.repository.findByCondominiumOrderByName(condominium, pageable)
                                .collectList()
                                .flatMap(list -> Mono.just(this.paginatedResponseMapper
                                        .toPaginator(this.mapper.toResponse(list), pageable.getPageNumber(), pageable.getPageSize(), total)))
                );
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.findById(id)
                //TODO: Verificar se existe apartamentos vinculados, se existir gerar erro
                .flatMap(this.repository::delete);
    }

}
