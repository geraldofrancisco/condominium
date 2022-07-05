package com.p2p.condominium.rest;

import com.p2p.condominium.document.AddressDocument;
import com.p2p.condominium.document.StackHolderDocument;
import com.p2p.condominium.dto.StackHolderInsertRequest;
import com.p2p.condominium.dto.StackHolderUpdateRequest;
import com.p2p.condominium.exception.CondominiumExceptionHandler;
import com.p2p.condominium.mapper.AddressMapper;
import com.p2p.condominium.mapper.PaginatedResponseMapper;
import com.p2p.condominium.mapper.PhoneMapper;
import com.p2p.condominium.mapper.StackHolderMapper;
import com.p2p.condominium.service.StackHolderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.UUID;

import static com.p2p.condominium.constant.ErrorConstant.REQUEST_CNPJ_INVALIDO;
import static com.p2p.condominium.constant.ErrorConstant.REQUEST_CPF_INVALIDO;
import static com.p2p.condominium.constant.ErrorConstant.REQUEST_EMAIL_INVALID;
import static com.p2p.condominium.constant.ErrorConstant.REQUEST_EMAIL_REQUIRED;
import static com.p2p.condominium.constant.ErrorConstant.REQUEST_ID_REQUIRED;
import static com.p2p.condominium.constant.ErrorConstant.REQUEST_NAME_REQUIRED;
import static com.p2p.condominium.enums.TypePersonEnum.FISICA;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {StackHolderController.class, CondominiumExceptionHandler.class})
@WebFluxTest(controllers = StackHolderController.class)
public class StackHolderControllerTest extends ControllerTest {

    @InjectMocks
    private StackHolderController controller;

    @MockBean
    private StackHolderService service;

    private static final String STACKHOLDER_URL = "/v1/stackholder";
    private static final String STACKHOLDER_URL_ID = STACKHOLDER_URL.concat("/{id}");

    private static final String INVALID_EMAIL = "email";

    @Before
    public void setup() {
        var stackHolderMapper = Mappers.getMapper(StackHolderMapper.class);
        var phoneMapper = Mappers.getMapper(PhoneMapper.class);
        var addressMapper = Mappers.getMapper(AddressMapper.class);
        ReflectionTestUtils.setField(stackHolderMapper, "addressMapper", addressMapper);
        ReflectionTestUtils.setField(stackHolderMapper, "phoneMapper", phoneMapper);
        ReflectionTestUtils.setField(controller, "stackHolderMapper", stackHolderMapper);
        ReflectionTestUtils.setField(controller, "service", service);
    }

    @Test
    public void testListSuccess() {
        when(service.findAll(any())).thenReturn(Mono.just(getReturnSuccessList().build()));
        this.client.get()
                .uri(STACKHOLDER_URL)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void getByIdSuccessTest() {
        when(service.findById(any())).thenReturn(Mono.just(getResponse().build()));
        final var result = controller.getById(UUID.randomUUID().toString());
        StepVerifier.create(result).assertNext(response -> assertNotNull(response)).verifyComplete();
    }

    @Test
    public void insertErrorCPFCNPJTest() {
        var request = getInsertRequest().cpf(null).cnpj(null).build();

        this.client.post()
                .uri(STACKHOLDER_URL)
                .contentType(APPLICATION_JSON)
                .body(Mono.just(request), StackHolderInsertRequest.class)
                .exchange()
                .expectStatus()
                .is4xxClientError();
    }

    @Test
    public void updateSuccessTest() {
        var request = getUpdateRequest().build();
        when(service.update(any())).thenReturn(Mono.just(StackHolderDocument.builder().build()));
        final var result = controller.update(request);
        StepVerifier.create(result).assertNext(response -> assertNotNull(response)).verifyComplete();
    }

    @Test
    public void updateErrorCPFCNPJTest() {
        var request = getUpdateRequest().cpf(null).cnpj(null).build();

        this.client.put()
                .uri(STACKHOLDER_URL)
                .contentType(APPLICATION_JSON)
                .body(Mono.just(request), StackHolderUpdateRequest.class)
                .exchange()
                .expectStatus()
                .is4xxClientError();
    }

    @Test
    public void insertSuccessTest() {
        var request = getInsertRequest().build();
        when(service.insert(any())).thenReturn(Mono.just(StackHolderDocument.builder().build()));
        final var result = controller.insert(request);
        StepVerifier.create(result).assertNext(response -> assertNotNull(response)).verifyComplete();
    }

    @Test
    public void deleteSuccessTest() {
        when(service.delete(anyString())).thenReturn(Mono.empty());
        this.client.delete()
                .uri(STACKHOLDER_URL_ID, randomUUID().toString())
                .exchange()
                .expectStatus()
                .isNoContent();
    }

    @Test
    public void errorInsertRequestBeanValidationTest() {
        var request = getInsertRequest()
                .cnpj("123")
                .cpf("123")
                .email(null)
                .name(null)
                .build();
        assertTrue(this.violation(request).stream().anyMatch(m -> m.getMessageTemplate().equals(REQUEST_CNPJ_INVALIDO)));
        assertTrue(this.violation(request).stream().anyMatch(m -> m.getMessageTemplate().equals(REQUEST_CPF_INVALIDO)));
        assertTrue(this.violation(request).stream().anyMatch(m -> m.getMessageTemplate().equals(REQUEST_NAME_REQUIRED)));
        assertTrue(this.violation(request).stream().anyMatch(m -> m.getMessageTemplate().equals(REQUEST_EMAIL_REQUIRED)));
        assertTrue(this.violation(getInsertRequest().email(INVALID_EMAIL).build()).stream().anyMatch(m -> m.getMessageTemplate().equals(REQUEST_EMAIL_INVALID)));
    }

    @Test
    public void errorUpdateRequestBeanValidationTest() {
        var request = getUpdateRequest()
                .id(null)
                .cnpj("123")
                .cpf("123")
                .email(null)
                .name(null)
                .build();
        assertTrue(this.violation(request).stream().anyMatch(m -> m.getMessageTemplate().equals(REQUEST_ID_REQUIRED)));
        assertTrue(this.violation(request).stream().anyMatch(m -> m.getMessageTemplate().equals(REQUEST_CNPJ_INVALIDO)));
        assertTrue(this.violation(request).stream().anyMatch(m -> m.getMessageTemplate().equals(REQUEST_CPF_INVALIDO)));
        assertTrue(this.violation(request).stream().anyMatch(m -> m.getMessageTemplate().equals(REQUEST_NAME_REQUIRED)));
        assertTrue(this.violation(request).stream().anyMatch(m -> m.getMessageTemplate().equals(REQUEST_EMAIL_REQUIRED)));
        assertTrue(this.violation(getUpdateRequest().email(INVALID_EMAIL).build()).stream().anyMatch(m -> m.getMessageTemplate().equals(REQUEST_EMAIL_INVALID)));
    }

    private StackHolderInsertRequest.StackHolderInsertRequestBuilder getInsertRequest() {
        return StackHolderInsertRequest.builder()
                .email("luke@email.com")
                .name("Luke Assunção Pimenta")
                .phones(new ArrayList<>())
                .cpf("74494917028");
    }

    private StackHolderUpdateRequest.StackHolderUpdateRequestBuilder getUpdateRequest() {
        return StackHolderUpdateRequest.builder()
                .id(randomUUID().toString())
                .email("luke@email.com")
                .name("Luke Assunção Pimenta")
                .phones(new ArrayList<>())
                .cpf("74494917028");
    }



    private StackHolderDocument.StackHolderDocumentBuilder getResponse() {
        return StackHolderDocument.builder()
                .id(randomUUID().toString())
                .address(AddressDocument.builder().build())
                .email("luke@email.com")
                .name("Luke Assunção Pimenta")
                .phones(new ArrayList<>())
                .typePersonEnum(FISICA)
                .identification("74494917028");
    }


}
