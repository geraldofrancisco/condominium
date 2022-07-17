package com.p2p.condominium.service.impl;

import com.p2p.condominium.document.ApartmentDocument;
import com.p2p.condominium.document.BuildingDocument;
import com.p2p.condominium.dto.ApartmentInsertRequest;
import com.p2p.condominium.dto.ApartmentOwnerRequest;
import com.p2p.condominium.dto.ApartmentResponse;
import com.p2p.condominium.dto.PaginatedResponse;
import com.p2p.condominium.exception.BusinessException;
import com.p2p.condominium.mapper.ApartmentMapper;
import com.p2p.condominium.mapper.PaginatedResponseMapper;
import com.p2p.condominium.repository.ApartmentRepository;
import com.p2p.condominium.repository.BuildingRepository;
import com.p2p.condominium.repository.StackHolderRepository;
import com.p2p.condominium.service.ApartmentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.p2p.condominium.constant.ErrorConstant.APARTMENT_BUILDING_NOT_EXISTS;
import static com.p2p.condominium.constant.ErrorConstant.APARTMENT_DONT_INSERT;
import static com.p2p.condominium.constant.ErrorConstant.APARTMENT_FLOOR_CROWDED;
import static com.p2p.condominium.constant.ErrorConstant.APARTMENT_FLOOR_MAX;
import static com.p2p.condominium.constant.ErrorConstant.APARTMENT_ID_NOT_EXIST;
import static com.p2p.condominium.constant.ErrorConstant.STACKHOLDER_ID_NOT_EXIST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
@AllArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {

    private ApartmentRepository repository;

    private ApartmentMapper mapper;

    private BuildingRepository buildingRepository;

    private StackHolderRepository stackHolderRepository;

    private PaginatedResponseMapper paginatedResponseMapper;


    @Override
    public Mono<Page<ApartmentResponse>> findAll(String building, Pageable pageable) {
        return this.repository.countByBuilding(building)
                .flatMap(total -> this.repository.findByBuildingOrderByFloorAscNumberAsc(building, pageable)
                        .collectList()
                        .flatMap(list -> Mono.just( new PageImpl<>(this.mapper.toResponse(list), pageable, total)))
                );
    }

    @Override
    public Mono<ApartmentDocument> findById(String id) {
        return this.repository.findById(id)
                .switchIfEmpty(Mono.error(new BusinessException(APARTMENT_ID_NOT_EXIST, NOT_FOUND)));
    }

    @Override
    public Mono<ApartmentDocument> insert(ApartmentInsertRequest request) {
        var document = this.mapper.insertToDocument(request);
        return Mono.just(document)
                .flatMap(this::validateExistsApartment)
                .flatMap(this::validateExistsBuilder)
                .flatMap(this::validatesTheNumberOfApartmentsOnTheFloor)
                .flatMap(this::validatesTheNumberOfFloorsInTheBuilding)
                .flatMap(this.repository::save);
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.findById(id)
                //TODO: verificar se não há vinculo do apartamento com outro objeto para deletar
                .flatMap(this.repository::delete);
    }

    private Mono<ApartmentDocument> validateExistsApartment(ApartmentDocument document) {
        return this.repository.existsByBuildingAndFloorAndNumber(document.getBuilding(), document.getFloor(), document.getNumber())
                .flatMap(exists -> {
                    if (!exists)
                        return Mono.empty();
                    return Mono.error(new BusinessException(APARTMENT_DONT_INSERT));
                }).thenReturn(document);
    }

    private Mono<Pair<ApartmentDocument, BuildingDocument>> validateExistsBuilder(ApartmentDocument document) {
        return this.buildingRepository.findById(document.getBuilding())
                .switchIfEmpty(Mono.error(new BusinessException(APARTMENT_BUILDING_NOT_EXISTS, NOT_FOUND)))
                .flatMap(building -> Mono.just(Pair.of(document, building)));
    }

    private Mono<Pair<ApartmentDocument, BuildingDocument>> validatesTheNumberOfApartmentsOnTheFloor(Pair<ApartmentDocument, BuildingDocument> pair) {
        var apartment = pair.getFirst();
        var building = pair.getSecond();
        return this.repository.countByBuildingAndFloor(apartment.getBuilding(), apartment.getFloor())
                .flatMap(count -> {
                    if (building.getNumberOfApartmentsPerFloor() <= count)
                        return Mono.error(new BusinessException(APARTMENT_FLOOR_CROWDED));
                    return Mono.empty();
                }).thenReturn(pair);
    }

    private Mono<ApartmentDocument> validatesTheNumberOfFloorsInTheBuilding(Pair<ApartmentDocument, BuildingDocument> pair) {
        var apartment = pair.getFirst();
        var building = pair.getSecond();
        if (building.getNumberOfFloors() < apartment.getFloor())
            return Mono.error(new BusinessException(APARTMENT_FLOOR_MAX));
        return Mono.just(apartment);
    }

    @Override
    public Mono<ApartmentDocument> assignApartmentOwner(ApartmentOwnerRequest request) {
        return this.stackHolderRepository.findById(request.getOwner())
                .switchIfEmpty(Mono.error(new BusinessException(STACKHOLDER_ID_NOT_EXIST, NOT_FOUND)))
                .flatMap(sh -> this.findById(request.getId()))
                .flatMap(document -> {
                    document.setOwner(request.getOwner());
                    return Mono.just(document);
                })
                .flatMap(this.repository::save);
    }

}
