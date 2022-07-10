package com.p2p.condominium.rest;

import com.p2p.condominium.dto.CondominiumDTO;
import com.p2p.condominium.dto.CondominiumResponse;
import com.p2p.condominium.dto.CondominiumUpdateRequest;
import com.p2p.condominium.dto.PaginatedResponse;
import com.p2p.condominium.mapper.CondominiumMapper;
import com.p2p.condominium.service.CondominiumService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import static com.p2p.condominium.constant.ControllerConstant.DEFAULT_PAGE;
import static com.p2p.condominium.constant.ControllerConstant.DEFAULT_SIZE;
import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1/condominium")
@RequiredArgsConstructor
@Validated
public class CondominiumController {
    private final CondominiumService service;

    private final CondominiumMapper condominiumMapper;

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
    public Mono<CondominiumResponse> getById(@PathVariable String id) {
        return this.service.findById(id)
                .map(condominiumMapper::toResponse);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Mono<CondominiumResponse> insert(@Valid @RequestBody CondominiumDTO request) {
        return service.insert(request)
                .map(condominiumMapper::toResponse);
    }

    @PutMapping
    @ResponseStatus(ACCEPTED)
    public Mono<CondominiumResponse> update(@Valid @RequestBody CondominiumUpdateRequest request) {
        return service.update(request)
                .map(condominiumMapper::toResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        return service.delete(id);
    }
}
