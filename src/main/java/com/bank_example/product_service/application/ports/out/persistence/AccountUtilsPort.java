package com.bank_example.product_service.application.ports.out.persistence;

import reactor.core.publisher.Mono;

public interface AccountUtilsPort {

    Mono<Long> getCountSavingAccounts(String clientId);
    Mono<Long> getCountCurrentAccounts(String clientId);
    Mono<Long> getCountFixedTermDeposit(String clientId);
}
