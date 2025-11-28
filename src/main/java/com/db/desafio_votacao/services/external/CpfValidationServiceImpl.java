package com.db.desafio_votacao.services.external;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
public class CpfValidationServiceImpl implements CpfValidationClient{
    private final WebClient webClient;

    public CpfValidationServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public boolean validateCpf(String cpf) {
        try {
            var response = webClient.get()
                    .uri("/external/cpf/{cpf}", cpf)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            return true;
        } catch (WebClientResponseException.NotFound ex) {
            return false;
        }
    }
}
