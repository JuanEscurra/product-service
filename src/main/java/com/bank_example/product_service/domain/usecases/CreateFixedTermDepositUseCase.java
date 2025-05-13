package com.bank_example.product_service.domain.usecases;

import com.bank_example.product_service.domain.generate.model.AccountResponse;
import com.bank_example.product_service.domain.generate.model.CreateFixedTermDepositRequest;
import reactor.core.publisher.Mono;

public interface CreateFixedTermDepositUseCase {

    Mono<AccountResponse> createFixedTermDeposit(CreateFixedTermDepositRequest request);

}
