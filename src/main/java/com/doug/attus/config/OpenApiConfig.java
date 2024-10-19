package com.doug.attus.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Attus - Sistema de Gerenciamento de Processos Jurídicos")
                        .version("1.0.0")
                        .description("API para Gerenciamento de Processos, Partes e Ações no contexto jurídico."));
    }
}
