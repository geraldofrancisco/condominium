package com.p2p.condominium.service.impl;

import com.p2p.condominium.document.ApartmentDocument;
import com.p2p.condominium.document.BuildingDocument;
import com.p2p.condominium.repository.ApartmentRepository;
import com.p2p.condominium.service.ApartmentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

import static java.util.stream.IntStream.range;

@Slf4j
@Service
@AllArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {

    private ApartmentRepository repository;

    @Override
    public Mono<Boolean> existsByBuilding(String building) {
        return this.repository.existsByBuilding(building);
    }
}
