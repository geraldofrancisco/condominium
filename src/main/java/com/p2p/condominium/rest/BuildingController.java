package com.p2p.condominium.rest;

import com.p2p.condominium.dto.BuildingDTO;
import com.p2p.condominium.dto.BuildingInsertRequest;
import com.p2p.condominium.dto.BuildingResponse;
import com.p2p.condominium.dto.BuildingUpdateRequest;
import com.p2p.condominium.dto.CondominiumDTO;
import com.p2p.condominium.dto.CondominiumUpdateRequest;
import com.p2p.condominium.dto.PaginatedResponse;
import com.p2p.condominium.mapper.BuildingMapper;
import com.p2p.condominium.service.BuildingService;
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
@RequestMapping("/v1/building")
@RequiredArgsConstructor
@Validated
public class BuildingController {
    private final BuildingService service;

    private final BuildingMapper mapper;

    @GetMapping
    @ResponseStatus(OK)
    public Mono<PaginatedResponse> list(
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE, required = false) int page,
            @RequestParam(name = "size", defaultValue = DEFAULT_SIZE, required = false) int size,
            @RequestParam(name = "condominium") String condominium
    ) {
        return this.service.findAll(of(page, size), condominium);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public Mono<BuildingResponse> getById(@PathVariable String id) {
        return this.service.findById(id)
                .map(this.mapper::toResponse);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Mono<BuildingResponse> insert(@Valid @RequestBody BuildingInsertRequest request) {
        return this.service.insert(this.mapper.toDTO(request))
                .map(this.mapper::toResponse);
    }

    @PutMapping
    @ResponseStatus(ACCEPTED)
    public Mono<BuildingResponse> update(@Valid @RequestBody BuildingUpdateRequest request) {
        return this.service.update(this.mapper.toDTO(request))
                .map(this.mapper::toResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        return this.service.delete(id);
    }
}
