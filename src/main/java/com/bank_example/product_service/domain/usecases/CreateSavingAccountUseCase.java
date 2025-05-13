package com.bank_example.product_service.domain.usecases;

import com.bank_example.product_service.domain.generate.model.AccountResponse;
import com.bank_example.product_service.domain.generate.model.CreateSavingAccountRequest;
import reactor.core.publisher.Mono;

public interface CreateSavingAccountUseCase {

    Mono<AccountResponse> createSavingAccount(CreateSavingAccountRequest request);

}
