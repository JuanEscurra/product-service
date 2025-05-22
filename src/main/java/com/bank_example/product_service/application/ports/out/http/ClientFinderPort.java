package com.bank_example.product_service.application.ports.out.http;

import com.bank_example.product_service.application.ports.out.http.request.Client;
import reactor.core.publisher.Mono;

public interface ClientFinderPort {

    Mono<Client> findById(String clientId);
}