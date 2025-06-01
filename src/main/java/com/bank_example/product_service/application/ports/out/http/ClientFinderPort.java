package com.bank_example.product_service.application.ports.out.http;

import com.bank_example.product_service.domain.models.client.Client;
import reactor.core.publisher.Mono;

public interface ClientFinderPort {

    Mono<Client> findById(String clientId);
}