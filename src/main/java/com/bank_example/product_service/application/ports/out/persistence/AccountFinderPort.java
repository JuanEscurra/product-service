package com.bank_example.product_service.application.ports.out.persistence;

import com.bank_example.product_service.domain.generate.model.AccountResponse;
import reactor.core.publisher.Mono;

public interface AccountFinderPort {

    Mono<AccountResponse> findAccountById(String id);
}
