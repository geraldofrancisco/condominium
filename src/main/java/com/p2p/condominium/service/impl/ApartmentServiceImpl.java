package com.p2p.condominium.service.impl;

import com.p2p.condominium.document.BuildingDocument;
import com.p2p.condominium.service.ApartmentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {

    @Async
    @Override
    public void createApartaments(BuildingDocument document)  {
        try {
            Thread.sleep(3000L);
            log.info("foi..........");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
