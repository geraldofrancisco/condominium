package com.p2p.condominium.rest;

import com.p2p.condominium.document.AddressDocument;
import com.p2p.condominium.document.CondominiumDocument;
import com.p2p.condominium.dto.Address;
import com.p2p.condominium.dto.CondominiumDTO;
import com.p2p.condominium.dto.CondominiumUpdateRequest;
import com.p2p.condominium.exception.BusinessExceptionHandler;
import com.p2p.condominium.service.CondominiumService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static com.p2p.condominium.constant.ErrorConstant.REQUEST_ADDRESS_REQUIRED;
import static com.p2p.condominium.constant.ErrorConstant.REQUEST_CNPJ_INVALIDO;
import static com.p2p.condominium.constant.ErrorConstant.REQUEST_CONSTRUCTION_COMPANY_REQUIRED;
import static com.p2p.condominium.constant.ErrorConstant.REQUEST_CPF_INVALIDO;
import static com.p2p.condominium.constant.ErrorConstant.REQUEST_EMAIL_INVALID;
import static com.p2p.condominium.constant.ErrorConstant.REQUEST_EMAIL_REQUIRED;
import static com.p2p.condominium.constant.ErrorConstant.REQUEST_IDENTIFICATION_REQUIRED;
import static com.p2p.condominium.constant.ErrorConstant.REQUEST_ID_REQUIRED;
import static com.p2p.condominium.constant.ErrorConstant.REQUEST_NAME_REQUIRED;
import static com.p2p.condominium.enums.StateEnum.MG;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CondominiumController.class, BusinessExceptionHandler.class})
@WebFluxTest(controllers = CondominiumController.class)
public class CondominiumControllerTest extends ControllerTest {

    @MockBean
    private CondominiumService service;

    private static final String CONDOMINIUM_URL = "/v1/condominium";

    private static final String CONDOMINIUM_URL_ID = CONDOMINIUM_URL.concat("/{id}");

    @Test
    public void testListSuccess() {
        when(service.findAll(any())).thenReturn(Mono.just(getReturnSuccessList().build()));
        this.client.get()
                .uri(CONDOMINIUM_URL)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void getByIdSuccessTest() {
        when(service.findById(any())).thenReturn(Mono.just(getResponse().build()));
        this.client.get()
                .uri(CONDOMINIUM_URL_ID, randomUUID().toString())
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void insertSuccessTest() {
        when(service.insert(any())).thenReturn(Mono.just(getResponse().build()));

        this.client.post()
                .uri(CONDOMINIUM_URL)
                .contentType(APPLICATION_JSON)
                .body(Mono.just(getRequest().build()), CondominiumDTO.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    public void updateSuccessTest() {
        var request = getRequest().id(UUID.randomUUID().toString()).build();
        when(service.update(any())).thenReturn(Mono.just(getResponse().build()));

        this.client.put()
                .uri(CONDOMINIUM_URL)
                .contentType(APPLICATION_JSON)
                .body(Mono.just(request), CondominiumDTO.class)
                .exchange()
                .expectStatus()
                .isAccepted();
    }

    @Test
    public void deleteSuccessTest() {
        when(service.delete(anyString())).thenReturn(Mono.empty());
        this.client.delete()
                .uri(CONDOMINIUM_URL_ID, randomUUID().toString())
                .exchange()
                .expectStatus()
                .isNoContent();
    }

    @Test
    public void errorInsertRequestBeanValidationTest() {
        var request = CondominiumDTO.builder().build();
        assertTrue(this.violation(getRequest().identification("123").build()).stream().anyMatch(m -> m.getMessageTemplate().equals(REQUEST_CNPJ_INVALIDO)));
        assertTrue(this.violation(request).stream().anyMatch(m -> m.getMessageTemplate().equals(REQUEST_IDENTIFICATION_REQUIRED)));
        assertTrue(this.violation(request).stream().anyMatch(m -> m.getMessageTemplate().equals(REQUEST_NAME_REQUIRED)));
        assertTrue(this.violation(request).stream().anyMatch(m -> m.getMessageTemplate().equals(REQUEST_CONSTRUCTION_COMPANY_REQUIRED)));
        assertTrue(this.violation(request).stream().anyMatch(m -> m.getMessageTemplate().equals(REQUEST_ADDRESS_REQUIRED)));
    }

    @Test
    public void errorUpdateRequestBeanValidationTest() {
        var request = new CondominiumUpdateRequest();
        assertTrue(this.violation(getRequest().identification("123").build()).stream().anyMatch(m -> m.getMessageTemplate().equals(REQUEST_CNPJ_INVALIDO)));
        assertTrue(this.violation(request).stream().anyMatch(m -> m.getMessageTemplate().equals(REQUEST_IDENTIFICATION_REQUIRED)));
        assertTrue(this.violation(request).stream().anyMatch(m -> m.getMessageTemplate().equals(REQUEST_ID_REQUIRED)));
        assertTrue(this.violation(request).stream().anyMatch(m -> m.getMessageTemplate().equals(REQUEST_NAME_REQUIRED)));
        assertTrue(this.violation(request).stream().anyMatch(m -> m.getMessageTemplate().equals(REQUEST_CONSTRUCTION_COMPANY_REQUIRED)));
        assertTrue(this.violation(request).stream().anyMatch(m -> m.getMessageTemplate().equals(REQUEST_ADDRESS_REQUIRED)));
    }

    private CondominiumDTO.CondominiumDTOBuilder getRequest() {
        return CondominiumDTO.builder()
                .address(Address.builder()
                        .zipCode("31235100")
                        .street("Rua Madureira")
                        .number(504)
                        .district("Aparecida")
                        .city("Belo Horizonte")
                        .state(MG).build())
                .constructionCompanyId(UUID.randomUUID().toString())
                .name("Parque Serra dos Ipês")
                .identification("58303228000130");
    }

    private CondominiumDocument.CondominiumDocumentBuilder getResponse() {
        return CondominiumDocument.builder()
                .id(randomUUID().toString())
                .identification("32834894000101")
                .name("Parque Serra dos Ipês")
                .constructionCompany(randomUUID().toString())
                .address(AddressDocument.builder()
                        .zipCode("31235100")
                        .street("Rua Madureira")
                        .number(504)
                        .district("Aparecida")
                        .city("Belo Horizonte")
                        .state(MG).build());
    }

}
