package com.p2p.condominium.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Condominium API",
        version = "1.0.0",
        description = "Microservice for condominium operations"
))
public class OpenApiConfiguration {

}
