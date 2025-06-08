package com.bank_example.product_service.application.services;

import com.bank_example.product_service.application.ports.out.persistence.AccountFinderPort;
import com.bank_example.product_service.domain.generate.model.AccountResponse;
import com.bank_example.product_service.domain.usecases.GetProductByIdUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetProductByIdUseCaseImpl implements GetProductByIdUseCase {

    private final AccountFinderPort accountFinder;

    @Override
    public Mono<AccountResponse> getProductById(String productId) {
        return this.accountFinder.findAccountById(productId)
                .doOnSuccess(account -> log.info("Get product by id: {}", productId))
                .doOnError(throwable -> log.error("Get product by id: {}", productId, throwable));
    }
}
