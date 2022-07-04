package com.p2p.condominium.rest;

import com.p2p.condominium.dto.PaginatedResponse;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Set;

public abstract class ControllerTest {

    @Autowired
    public WebTestClient client;

    private Validator validator;

    @Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    protected Set<ConstraintViolation<Object>> violation(final Object request) {
        return validator.validate(request);
    }

    protected PaginatedResponse.PaginatedResponseBuilder getReturnSuccessList() {
        return PaginatedResponse.builder()
                .content(new ArrayList())
                .page(0)
                .size(1)
                .totalRecords(1L);
    }
}
