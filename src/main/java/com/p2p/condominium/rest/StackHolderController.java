package com.p2p.condominium.rest;

import com.p2p.condominium.builder.StackHolderBuilder;
import com.p2p.condominium.dto.PaginatedResponse;
import com.p2p.condominium.dto.StackHolderDTO;
import com.p2p.condominium.service.StackHolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static com.p2p.condominium.constant.ControllerConstant.DEFAULT_PAGE;
import static com.p2p.condominium.constant.ControllerConstant.DEFAULT_SIZE;
import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1/stack-holder")
@RequiredArgsConstructor
public class StackHolderController {

    private final StackHolderService service;

    @GetMapping
    @ResponseStatus(OK)
    public Mono<PaginatedResponse> list(
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE, required = false) int page,
            @RequestParam(name = "size", defaultValue = DEFAULT_SIZE, required = false) int size
    ) {
        return this.service.findAll(of(page, size));
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public Mono<StackHolderDTO> getById(@PathVariable String id) {
        return this.service.findById(id)
                .map(StackHolderBuilder::toDTO);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Mono<StackHolderDTO> insert(@RequestBody StackHolderDTO request) {
        return service.insert(request)
                .map(StackHolderBuilder::toDTO);
    }
}
