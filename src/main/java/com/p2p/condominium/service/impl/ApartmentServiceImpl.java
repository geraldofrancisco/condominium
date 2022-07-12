package com.p2p.condominium.service.impl;

import com.p2p.condominium.repository.ApartmentRepository;
import com.p2p.condominium.service.ApartmentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {

    private ApartmentRepository repository;

}
