package com.p2p.condominium.service;

import com.p2p.condominium.document.AddressDocument;
import com.p2p.condominium.document.StackHolderDocument;
import com.p2p.condominium.dto.Address;
import com.p2p.condominium.dto.StackHolderInsertRequest;
import com.p2p.condominium.dto.StackHolderUpdateRequest;
import com.p2p.condominium.exception.BusinessException;
import com.p2p.condominium.mapper.AddressMapper;
import com.p2p.condominium.mapper.PhoneMapper;
import com.p2p.condominium.mapper.StackHolderMapper;
import com.p2p.condominium.repository.StackHolderRepository;
import com.p2p.condominium.service.impl.StackHolderServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.UUID;

import static com.p2p.condominium.enums.TypePersonEnum.FISICA;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StackHolderServiceTest {

    @InjectMocks
    private StackHolderServiceImpl service;

    @Mock
    private StackHolderRepository repository;

    @Before
    public void before() {
        var stackHolderMapper = Mappers.getMapper(StackHolderMapper.class);
        var phoneMapper = Mappers.getMapper(PhoneMapper.class);
        var addressMapper = Mappers.getMapper(AddressMapper.class);
        ReflectionTestUtils.setField(stackHolderMapper, "addressMapper", addressMapper);
        ReflectionTestUtils.setField(stackHolderMapper, "phoneMapper", phoneMapper);
        ReflectionTestUtils.setField(service, "mapper", stackHolderMapper);
    }

    @Test
    public void findAllSuccess() {
        var pageable = PageRequest.of(0, 1);
        when(repository.count()).thenReturn(Mono.just(1L));
        when(repository.findByIdNotNullOrderByNameAsc(pageable)).thenReturn(Flux.just(getDocumentReturn().build()));

        final var result = this.service.findAll(pageable);
        StepVerifier.create(result).assertNext(response -> assertNotNull(response)).verifyComplete();
    }

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
    public void findByPhysicalPersonAndIdSuccessTest() {
        when(repository.findByTypePersonEnumAndId(any(), anyString())).thenReturn(Mono.just(getDocumentReturn().build()));
        final var result = this.service.findByPhysicalPersonAndId(UUID.randomUUID().toString());
        StepVerifier.create(result).assertNext(response -> assertNotNull(response)).verifyComplete();
    }

    @Test
    public void findByPhysicalPersonAndIdErrorTest() {
        when(repository.findByTypePersonEnumAndId(any(), anyString())).thenReturn(Mono.empty());
        final var result = this.service.findByPhysicalPersonAndId(UUID.randomUUID().toString());
        StepVerifier.create(result).expectError(BusinessException.class).verify();
    }

    @Test
    public void findByLegalPersonAndIdSuccessTest() {
        when(repository.findByTypePersonEnumAndId(any(), anyString())).thenReturn(Mono.just(getDocumentReturn().build()));
        final var result = this.service.findByLegalPersonAndId(UUID.randomUUID().toString());
        StepVerifier.create(result).assertNext(response -> assertNotNull(response)).verifyComplete();
    }

    @Test
    public void findByLegalPersonAndIdErrorTest() {
        when(repository.findByTypePersonEnumAndId(any(), anyString())).thenReturn(Mono.empty());
        final var result = this.service.findByLegalPersonAndId(UUID.randomUUID().toString());
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
                .typePersonEnum(FISICA)
                .phones(new ArrayList<>());
    }

}
