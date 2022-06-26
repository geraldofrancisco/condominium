package com.p2p.condominium.service;

import com.p2p.condominium.document.AddressDocument;
import com.p2p.condominium.document.StackHolderDocument;
import com.p2p.condominium.dto.Address;
import com.p2p.condominium.dto.StackHolderInsertRequest;
import com.p2p.condominium.dto.StackHolderUpdateRequest;
import com.p2p.condominium.enums.TypePersonEnum;
import com.p2p.condominium.exception.BusinessException;
import com.p2p.condominium.repository.StackHolderRepository;
import com.p2p.condominium.service.impl.StackHolderServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class StackHolderServiceTest {

    @InjectMocks
    private StackHolderServiceImpl service;

    @Mock
    private StackHolderRepository repository;

    @Test
    public void insertSuccess() {
        when(repository.findByIdentification(anyString())).thenReturn(Mono.empty());
        when(repository.save(any())).thenReturn(Mono.just(getDocumentReturn().build()));
        final var result = this.service.insert(getInsertRequest().build());
        StepVerifier.create(result)
                .assertNext(response -> {
                    assertNotNull(response);
                    assertNotNull(response.getId());
                }).verifyComplete();
    }

    @Test
    public void findByIdSuccessTest() {
        when(repository.findById(anyString())).thenReturn(Mono.just(getDocumentReturn().build()));
        final var result = this.service.findById(anyString());
        StepVerifier.create(result).assertNext(response -> assertNotNull(response)).verifyComplete();
    }

    @Test
    public void findByIdErrorTest() {
        when(repository.findById(anyString())).thenReturn(Mono.empty());
        final var result = this.service.findById(anyString());
        StepVerifier.create(result).expectError(BusinessException.class).verify();
    }

    @Test
    public void updateSuccessTest() {
        var document = getDocumentReturn().build();
        var request = getUpdateRequest().build();
        when(repository.findById(anyString())).thenReturn(Mono.just(document));
        when(repository.save(any())).thenReturn(Mono.just(document));
        final var result = this.service.update(request);
        StepVerifier.create(result).assertNext(response -> assertNotNull(response)).verifyComplete();
    }

    @Test
    public void deleteSuccessTest() {
        var document = getDocumentReturn().build();
        when(repository.findById(anyString())).thenReturn(Mono.just(document));
        when(repository.delete(any())).thenReturn(Mono.empty().then());
        final var result = this.service.delete(anyString());
        StepVerifier.create(result).expectComplete().verify();
    }

    private StackHolderUpdateRequest.StackHolderUpdateRequestBuilder getUpdateRequest() {
        return StackHolderUpdateRequest.builder()
                .id(randomUUID().toString())
                .cpf("98487828000")
                .email("email@email.com")
                .name("Gihyondîs")
                .address(Address.builder().build())
                .phones(new ArrayList<>());
    }

    private StackHolderInsertRequest.StackHolderInsertRequestBuilder getInsertRequest() {
        return StackHolderInsertRequest.builder()
                .cpf("98487828000")
                .email("email@email.com")
                .name("Gihyondîs")
                .address(Address.builder().build())
                .phones(new ArrayList<>());
    }


    private StackHolderDocument.StackHolderDocumentBuilder getDocumentReturn() {
        return StackHolderDocument.builder()
                .id(randomUUID().toString())
                .name("Gihyondîs")
                .address(AddressDocument.builder().build())
                .email("email@email.com")
                .identification("98487828000")
                .typePersonEnum(TypePersonEnum.FISICA)
                .phones(new ArrayList<>());
    }

}
