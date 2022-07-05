package com.p2p.condominium.service;

import com.p2p.condominium.mapper.AddressMapper;
import com.p2p.condominium.mapper.CondominiumManagerMapper;
import com.p2p.condominium.mapper.CondominiumMapper;
import com.p2p.condominium.mapper.PaginatedResponseMapper;
import com.p2p.condominium.repository.CondominiumRepository;
import com.p2p.condominium.repository.StackHolderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CondominiumServiceTest {

    @InjectMocks
    private CondominiumService service;

    @Mock
    private CondominiumRepository repository;

    @Mock
    private StackHolderRepository stackHolderRepository;

    @Before
    public void setup() {
        var paginatedResponseMapper = Mappers.getMapper(PaginatedResponseMapper.class);
        var condominiumMapper = Mappers.getMapper(CondominiumMapper.class);
        var addressMapper = Mappers.getMapper(AddressMapper.class);
        var condominiumManagerMapper = Mappers.getMapper(CondominiumManagerMapper.class);
        ReflectionTestUtils.setField(condominiumMapper, "addressMapper", addressMapper);
        ReflectionTestUtils.setField(condominiumMapper, "condominiumManagerMapper", condominiumManagerMapper);
        ReflectionTestUtils.setField(service, "paginatedResponseMapper", paginatedResponseMapper);
        ReflectionTestUtils.setField(service, "condominiumMapper", condominiumMapper);
    }

    @Test
    public void insertSuccessTest() {
        when(repository.findByIdentification(anyString())).thenReturn(Mono.empty());
    }


}
