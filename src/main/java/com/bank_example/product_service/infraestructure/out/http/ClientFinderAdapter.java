package com.bank_example.product_service.infraestructure.out.http;

import com.bank_example.product_service.application.ports.out.http.ClientFinderPort;
import com.bank_example.product_service.application.ports.out.http.request.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Component
@RequiredArgsConstructor
public class ClientFinderAdapter implements ClientFinderPort {


    private final WebClient userWebClient;

    @Override
    public Mono<Client> findById(String clientId) {
        return this.userWebClient.get()
                .uri("/{clientId}", clientId)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new Exception("Client not found")))
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new Exception("Server error")))
                .bodyToMono(Client.class);
    }
}
