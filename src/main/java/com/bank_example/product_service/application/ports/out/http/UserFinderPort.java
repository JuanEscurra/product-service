package com.bank_example.product_service.application.ports.out.http;

import com.bank_example.product_service.application.ports.out.http.request.User;
import reactor.core.publisher.Mono;

public interface UserFinderPort {

    Mono<User> findById(String clientId);
}