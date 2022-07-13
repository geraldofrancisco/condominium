package com.p2p.condominium.rest;

import com.p2p.condominium.dto.ExceptionResponse;
import com.p2p.condominium.dto.PaginatedResponse;
import com.p2p.condominium.dto.StackHolderInsertRequest;
import com.p2p.condominium.dto.StackHolderResponse;
import com.p2p.condominium.dto.StackHolderUpdateRequest;
import com.p2p.condominium.exception.BusinessException;
import com.p2p.condominium.mapper.StackHolderMapper;
import com.p2p.condominium.service.StackHolderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
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
import static com.p2p.condominium.constant.ControllerConstant.ID;
import static com.p2p.condominium.constant.ControllerConstant.ID_DESCRIPTION;
import static com.p2p.condominium.constant.ControllerConstant.MEDIA_TYPE_JSON;
import static com.p2p.condominium.constant.ControllerConstant.PAGE;
import static com.p2p.condominium.constant.ControllerConstant.PAGE_DESCRIPTION;
import static com.p2p.condominium.constant.ControllerConstant.SIZE;
import static com.p2p.condominium.constant.ControllerConstant.SIZE_DESCRIPTION;
import static com.p2p.condominium.constant.ControllerConstant.STACKHOLDER;
import static com.p2p.condominium.constant.ControllerConstant.STACKHOLDER_DESCRIPTION;
import static com.p2p.condominium.constant.ControllerConstant.STACKHOLDER_OPERATION_DELETE_DESCRIPTION;
import static com.p2p.condominium.constant.ControllerConstant.STACKHOLDER_OPERATION_DELETE_SUMMARY;
import static com.p2p.condominium.constant.ControllerConstant.STACKHOLDER_OPERATION_GET_ID_DESCRIPTION;
import static com.p2p.condominium.constant.ControllerConstant.STACKHOLDER_OPERATION_GET_ID_SUMMARY;
import static com.p2p.condominium.constant.ControllerConstant.STACKHOLDER_OPERATION_LIST_DESCRIPTION;
import static com.p2p.condominium.constant.ControllerConstant.STACKHOLDER_OPERATION_LIST_SUMMARY;
import static com.p2p.condominium.constant.ControllerConstant.STATUS_BAD_REQUEST;
import static com.p2p.condominium.constant.ControllerConstant.STATUS_BAD_REQUEST_DESCRIPTION;
import static com.p2p.condominium.constant.ControllerConstant.STATUS_NOT_FOUND;
import static com.p2p.condominium.constant.ControllerConstant.STATUS_NOT_FOUND_DESCRIPTION;
import static com.p2p.condominium.constant.ControllerConstant.STATUS_NO_CONTENT;
import static com.p2p.condominium.constant.ControllerConstant.STATUS_NO_CONTENT_DESCRIPTION;
import static com.p2p.condominium.constant.ControllerConstant.STATUS_OK;
import static com.p2p.condominium.constant.ControllerConstant.STATUS_OK_DESCRIPTION;
import static com.p2p.condominium.constant.ErrorConstant.CNPJ_OR_CPF_REQUIRED;
import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1/stackholder")
@RequiredArgsConstructor
@Tag(name = STACKHOLDER, description = STACKHOLDER_DESCRIPTION)
@Validated
public class StackHolderController {

    private final StackHolderService service;

    private final StackHolderMapper stackHolderMapper;

    @GetMapping
    @ResponseStatus(OK)
    @Operation(
            summary = STACKHOLDER_OPERATION_LIST_SUMMARY,
            description = STACKHOLDER_OPERATION_LIST_DESCRIPTION,
            parameters = {
                    @Parameter(name = PAGE, description = PAGE_DESCRIPTION),
                    @Parameter(name = SIZE, description = SIZE_DESCRIPTION)
            },
            responses = {
                    @ApiResponse(
                            responseCode = STATUS_OK,
                            description = STATUS_OK_DESCRIPTION,
                            content = @Content(
                                    mediaType = MEDIA_TYPE_JSON,
                                    schema = @Schema(implementation = PaginatedResponse.class)
                            )
                    )
            }
    )
    public Mono<PaginatedResponse> list(
            @RequestParam(name = PAGE, defaultValue = DEFAULT_PAGE, required = false) int page,
            @RequestParam(name = SIZE, defaultValue = DEFAULT_SIZE, required = false) int size
    ) {
        return this.service.findAll(of(page, size));
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    @Operation(
            summary = STACKHOLDER_OPERATION_GET_ID_SUMMARY,
            description = STACKHOLDER_OPERATION_GET_ID_DESCRIPTION,
            parameters = @Parameter(name = ID, description = ID_DESCRIPTION, required = true),
            responses = {
                    @ApiResponse(
                            responseCode = STATUS_OK,
                            description = STATUS_OK_DESCRIPTION,
                            content = @Content(
                                    mediaType = MEDIA_TYPE_JSON,
                                    schema = @Schema(implementation = StackHolderResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = STATUS_NOT_FOUND,
                            description = STATUS_NOT_FOUND_DESCRIPTION,
                            content = @Content(
                                    mediaType = MEDIA_TYPE_JSON,
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    )
            }
    )
    public Mono<StackHolderResponse> getById(@PathVariable String id) {
        return this.service.findById(id)
                .map(stackHolderMapper::toResponse);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Mono<StackHolderResponse> insert(@Valid @RequestBody StackHolderInsertRequest request) {
        if (StringUtils.isBlank(request.getCpf()) && StringUtils.isBlank(request.getCnpj()))
            return Mono.error(new BusinessException(CNPJ_OR_CPF_REQUIRED));

        return service.insert(request)
                .map(stackHolderMapper::toResponse);
    }

    @PutMapping
    @ResponseStatus(ACCEPTED)
    public Mono<StackHolderResponse> update(@Valid @RequestBody StackHolderUpdateRequest request) {
        if (StringUtils.isBlank(request.getCpf()) && StringUtils.isBlank(request.getCnpj()))
            return Mono.error(new BusinessException(CNPJ_OR_CPF_REQUIRED));

        return service.update(request)
                .map(stackHolderMapper::toResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(
            summary = STACKHOLDER_OPERATION_DELETE_SUMMARY,
            description = STACKHOLDER_OPERATION_DELETE_DESCRIPTION,
            parameters = @Parameter(name = ID, description = ID_DESCRIPTION, required = true),
            responses = {
                    @ApiResponse(
                            responseCode = STATUS_NO_CONTENT,
                            description = STATUS_NO_CONTENT_DESCRIPTION
                    ),
                    @ApiResponse(
                            responseCode = STATUS_NOT_FOUND,
                            description = STATUS_NOT_FOUND_DESCRIPTION,
                            content = @Content(
                                    mediaType = MEDIA_TYPE_JSON,
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = STATUS_BAD_REQUEST,
                            description = STATUS_BAD_REQUEST_DESCRIPTION,
                            content = @Content(
                                    mediaType = MEDIA_TYPE_JSON,
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    )
            }
    )
    public Mono<Void> delete(@PathVariable String id) {
        return service.delete(id);
    }
}
