package com.bank_example.product_service.application.services;

import com.bank_example.product_service.application.ports.out.persistence.AccountFinderPort;
import com.bank_example.product_service.domain.generate.model.AccountResponse;
import com.bank_example.product_service.domain.usecases.GetProductByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GetProductByIdUseCaseImpl implements GetProductByIdUseCase {

    private final AccountFinderPort accountFinder;

    @Override
    public Mono<AccountResponse> getProductById(String productId) {
        return this.accountFinder.findAccountById(productId);
    }
}
