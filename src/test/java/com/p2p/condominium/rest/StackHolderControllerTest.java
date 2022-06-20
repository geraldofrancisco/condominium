package com.p2p.condominium.rest;

import com.p2p.condominium.dto.PaginatedResponse;
import com.p2p.condominium.repository.StackHolderRepository;
import com.p2p.condominium.service.StackHolderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import javax.validation.Validator;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {StackHolderController.class})
@WebFluxTest(controllers = StackHolderController.class)
public class StackHolderControllerTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    private StackHolderService service;

    @MockBean
    private StackHolderRepository repository;

    private Validator validator;

    private static final String STACKHOLDER_URL = "/v1/stack-holder";

    @Test
    public void testListSuccess() {
        Mockito.when(service.findAll(Mockito.any())).thenReturn(Mono.just(getReturn().build()));
        this.client.get()
                .uri(STACKHOLDER_URL)
                .exchange()
                .expectStatus()
                .isOk();
    }

    private PaginatedResponse.PaginatedResponseBuilder getReturn() {
        return PaginatedResponse.builder()
                .content(new ArrayList())
                .page(0)
                .size(1)
                .totalRecords(1L);
    }
}
