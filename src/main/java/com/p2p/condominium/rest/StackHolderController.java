package com.p2p.condominium.rest;

import com.p2p.condominium.builder.StackHolderBuilder;
import com.p2p.condominium.dto.StackHolderDTO;
import com.p2p.condominium.service.StackHolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/stack-holder")
@RequiredArgsConstructor
public class StackHolderController {

    private final StackHolderService service;

    @PostMapping
    @ResponseStatus(CREATED)
    public Mono<StackHolderDTO> save(@RequestBody StackHolderDTO request) {
        return service.insert(request)
                .map(StackHolderBuilder::toDTO);
    }
}
