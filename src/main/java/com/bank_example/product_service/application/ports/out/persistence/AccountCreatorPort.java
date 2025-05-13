package com.bank_example.product_service.application.ports.out.persistence;

import com.bank_example.product_service.domain.generate.model.AccountResponse;
import com.bank_example.product_service.domain.generate.model.CreateFixedTermDepositRequest;
import reactor.core.publisher.Mono;

public interface AccountCreatorPort {

    Mono<AccountResponse> createSavingAccount(String clientId);
    Mono<AccountResponse> createCurrentAccount(String clientId);
    Mono<AccountResponse> createFixedTermDeposit(CreateFixedTermDepositRequest request);
}
