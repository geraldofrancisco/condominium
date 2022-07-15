package com.p2p.condominium.rest;

import com.p2p.condominium.dto.ApartmentInsertRequest;
import com.p2p.condominium.dto.ApartmentResponse;
import com.p2p.condominium.dto.ApartmentUpdateRequest;
import com.p2p.condominium.dto.PaginatedResponse;
import com.p2p.condominium.mapper.ApartmentMapper;
import com.p2p.condominium.service.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
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
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1/apartment")
@RequiredArgsConstructor
@Validated
public class ApartmentController {
    private final ApartmentService service;

    private final ApartmentMapper mapper;

    @GetMapping
    @ResponseStatus(OK)
    public Mono<PaginatedResponse> list(
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE, required = false) int page,
            @RequestParam(name = "size", defaultValue = DEFAULT_SIZE, required = false) int size,
            @RequestParam(name = "building") String building
    ) {
        return this.service.findAll(building, of(page, size));
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public Mono<ApartmentResponse> getById(@PathVariable String id) {
        return this.service.findById(id)
                .map(this.mapper::toResponse);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Mono<ApartmentResponse> insert(@Valid @RequestBody ApartmentInsertRequest request) {
        return this.service.insert(request)
                .map(this.mapper::toResponse);
    }

    @PutMapping
    @ResponseStatus(ACCEPTED)
    public Mono<ApartmentResponse> update(@Valid @RequestBody ApartmentUpdateRequest request) {
        return this.service.update(request)
                .map(this.mapper::toResponse);
    }
}
