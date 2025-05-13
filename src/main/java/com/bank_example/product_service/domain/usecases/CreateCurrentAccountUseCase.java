package com.bank_example.product_service.domain.usecases;

import com.bank_example.product_service.domain.generate.model.AccountResponse;
import com.bank_example.product_service.domain.generate.model.CreateCurrentAccountRequest;
import reactor.core.publisher.Mono;

public interface CreateCurrentAccountUseCase {

    Mono<AccountResponse> createCurrentAccount(CreateCurrentAccountRequest request);
}
