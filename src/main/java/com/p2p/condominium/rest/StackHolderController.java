package com.p2p.condominium.rest;

import com.p2p.condominium.builder.StackHolderBuilder;
import com.p2p.condominium.dto.PaginatedResponse;
import com.p2p.condominium.dto.StackHolderResponse;
import com.p2p.condominium.dto.StackHolderInsertRequest;
import com.p2p.condominium.exception.BusinessException;
import com.p2p.condominium.service.StackHolderService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import static com.p2p.condominium.constant.ControllerConstant.DEFAULT_PAGE;
import static com.p2p.condominium.constant.ControllerConstant.DEFAULT_SIZE;
import static com.p2p.condominium.constant.ErrorConstant.CNPJ_OR_CPF_REQUIRED;
import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1/stack-holder")
@RequiredArgsConstructor
@Validated
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
    public Mono<StackHolderResponse> getById(@PathVariable String id) {
        return this.service.findById(id)
                .map(StackHolderBuilder::toResponse);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Mono<StackHolderResponse> insert(@Valid @RequestBody StackHolderInsertRequest request) {
        if(StringUtils.isBlank(request.getCpf()) && StringUtils.isBlank(request.getCnpj()))
            return Mono.error(new BusinessException(CNPJ_OR_CPF_REQUIRED));

        return service.insert(request)
                .map(StackHolderBuilder::toResponse);
    }
}
