package com.p2p.condominium.rest;

import com.p2p.condominium.dto.PaginatedResponse;
import com.p2p.condominium.mapper.BuildingMapper;
import com.p2p.condominium.service.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static com.p2p.condominium.constant.ControllerConstant.DEFAULT_PAGE;
import static com.p2p.condominium.constant.ControllerConstant.DEFAULT_SIZE;
import static org.springframework.data.domain.PageRequest.of;
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
}
