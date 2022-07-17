package com.p2p.condominium.service;

import com.p2p.condominium.document.AddressDocument;
import com.p2p.condominium.document.CondominiumDocument;
import com.p2p.condominium.document.StackHolderDocument;
import com.p2p.condominium.dto.Address;
import com.p2p.condominium.dto.CondominiumDTO;
import com.p2p.condominium.exception.BusinessException;
import com.p2p.condominium.mapper.AddressMapper;
import com.p2p.condominium.mapper.CondominiumManagerMapper;
import com.p2p.condominium.mapper.CondominiumMapper;
import com.p2p.condominium.repository.CondominiumRepository;
import com.p2p.condominium.service.impl.CondominiumServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
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
public class CondominiumServiceTest {

    @InjectMocks
    private CondominiumServiceImpl service;

    @Spy
    private StackHolderService stackHolderService;

    @Mock
    private CondominiumRepository repository;

    @Before
    public void setup() {
        var condominiumMapper = Mappers.getMapper(CondominiumMapper.class);
        var addressMapper = Mappers.getMapper(AddressMapper.class);
        var condominiumManagerMapper = Mappers.getMapper(CondominiumManagerMapper.class);
        ReflectionTestUtils.setField(condominiumMapper, "addressMapper", addressMapper);
        ReflectionTestUtils.setField(condominiumMapper, "condominiumManagerMapper", condominiumManagerMapper);
        ReflectionTestUtils.setField(service, "mapper", condominiumMapper);
    }

    @Test
    public void insertSuccessTest() {
        var stackHolderDocument = getStackHolderDocumentReturn().build();
        when(repository.findByIdentification(anyString())).thenReturn(Mono.empty());
        when(stackHolderService.findByLegalPersonAndId(anyString())).thenReturn(Mono.just(stackHolderDocument));
        when(repository.save(any()))
                .thenReturn(Mono.just(getCondominiumDocument().id(UUID.randomUUID().toString()).build()));
        var result = this.service.insert(getCondominiumDTO().build());
        StepVerifier.create(result).assertNext(response -> assertNotNull(response)).verifyComplete();
    }

    @Test
    public void updateSuccessTest() {
        var stackHolderDocument = getStackHolderDocumentReturn().build();
        var condominiumDocument = getCondominiumDocument().id(UUID.randomUUID().toString()).build();
        when(repository.findById(anyString())).thenReturn(Mono.just(condominiumDocument));
        when(stackHolderService.findByLegalPersonAndId(anyString())).thenReturn(Mono.just(stackHolderDocument));
        when(repository.save(any()))
                .thenReturn(Mono.just(getCondominiumDocument().id(UUID.randomUUID().toString()).build()));
        var result = this.service.update(getCondominiumDTO().id(UUID.randomUUID().toString()).build());
        StepVerifier.create(result).assertNext(response -> assertNotNull(response)).verifyComplete();
    }

    @Test
    public void findByIdSuccessTest() {
        var id = UUID.randomUUID().toString();
        when(repository.findById(id)).thenReturn(Mono.just(getCondominiumDocument().build()));
        var result = this.service.findById(id);
        StepVerifier.create(result).assertNext(response -> assertNotNull(response)).verifyComplete();
    }

    @Test
    public void findByIdErrorTest() {
        var id = UUID.randomUUID().toString();
        when(repository.findById(id)).thenReturn(Mono.empty());
        var result = this.service.findById(id);
        StepVerifier.create(result).expectError(BusinessException.class).verify();
    }

    @Test
    public void findAllSuccessTest() {
        var pageable = PageRequest.of(0, 1);
        when(repository.count()).thenReturn(Mono.just(1L));
        when(repository.findByIdNotNullOrderByNameAsc(pageable)).thenReturn(Flux.just(getCondominiumDocument().build()));

        final var result = this.service.findAll(pageable);
        StepVerifier.create(result).assertNext(response -> assertNotNull(response)).verifyComplete();
    }

    @Test
    public void deleteSuccessTest() {
        var document = getCondominiumDocument().build();
        when(repository.findById(anyString())).thenReturn(Mono.just(document));
        when(repository.delete(any())).thenReturn(Mono.empty().then());
        final var result = this.service.delete(anyString());
        StepVerifier.create(result).expectComplete().verify();
    }

    private CondominiumDocument.CondominiumDocumentBuilder getCondominiumDocument() {
        return CondominiumDocument.builder()

                .address(AddressDocument.builder().build())
                .name("Parque Serra dos Ipês")
                .constructionCompany(UUID.randomUUID().toString())
                .identification("79765474000184");
    }

    private CondominiumDTO.CondominiumDTOBuilder getCondominiumDTO() {
        return CondominiumDTO.builder()
                .address(Address.builder().build())
                .name("Parque Serra dos Ipês")
                .constructionCompanyId(UUID.randomUUID().toString())
                .identification("79765474000184");
    }

    private StackHolderDocument.StackHolderDocumentBuilder getStackHolderDocumentReturn() {
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
