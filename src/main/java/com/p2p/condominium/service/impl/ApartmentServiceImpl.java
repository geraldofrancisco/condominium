package com.p2p.condominium.service.impl;

import com.p2p.condominium.document.ApartmentDocument;
import com.p2p.condominium.dto.ApartmentInsertRequest;
import com.p2p.condominium.dto.ApartmentUpdateRequest;
import com.p2p.condominium.dto.PaginatedResponse;
import com.p2p.condominium.exception.BusinessException;
import com.p2p.condominium.repository.ApartmentRepository;
import com.p2p.condominium.repository.BuildingRepository;
import com.p2p.condominium.service.ApartmentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.p2p.condominium.constant.ErrorConstant.APARTMENT_ID_NOT_EXIST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
@AllArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {

    private ApartmentRepository repository;

    private BuildingRepository buildingRepository;


    @Override
    public Mono<PaginatedResponse> findAll(String builder, Pageable pageable) {
        return null;
    }

    @Override
    public Mono<ApartmentDocument> findById(String id) {
        return this.repository.findById(id)
                .switchIfEmpty(Mono.error(new BusinessException(APARTMENT_ID_NOT_EXIST, NOT_FOUND)));
    }

    @Override
    public Mono<ApartmentDocument> insert(ApartmentInsertRequest request) {
        return null;
    }

    @Override
    public Mono<ApartmentDocument> update(ApartmentUpdateRequest request) {
        return null;
    }

}
