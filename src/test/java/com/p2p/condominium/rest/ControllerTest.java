package com.p2p.condominium.rest;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    public void init() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    protected Set<ConstraintViolation<Object>> violation(final Object request) {
        return validator.validate(request);
    }

    protected Page getReturnSuccessList() {
        return new PageImpl(new ArrayList());
    }
}
