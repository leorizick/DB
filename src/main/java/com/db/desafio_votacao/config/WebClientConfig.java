package com.db.desafio_votacao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient cpfWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8080")
                .build();
    }
}
