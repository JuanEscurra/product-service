package com.bank_example.product_service.domain.usecases;

import com.bank_example.product_service.domain.generate.model.AccountResponse;
import reactor.core.publisher.Mono;

public interface GetProductByIdUseCase {

    Mono<AccountResponse> getProductById(String productId);
}
