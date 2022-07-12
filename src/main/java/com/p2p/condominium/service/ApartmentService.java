package com.p2p.condominium.service;

import reactor.core.publisher.Mono;

public interface ApartmentService {
    Mono<Boolean> existsByBuilding(String building);
}
