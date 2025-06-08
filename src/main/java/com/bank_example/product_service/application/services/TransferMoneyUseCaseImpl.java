package com.bank_example.product_service.application.services;

import com.bank_example.product_service.application.ports.out.persistence.AccountUpdaterPort;
import com.bank_example.product_service.domain.generate.model.TransferMoneyRequest;
import com.bank_example.product_service.domain.usecases.TransferMoneyUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransferMoneyUseCaseImpl implements TransferMoneyUseCase {

    private final AccountUpdaterPort accountUpdater;


    @Override
    public Mono<Void> transferMoney(TransferMoneyRequest request) {
        return Mono.when(
                accountUpdater.changeAmount(request.getProductOriginId(), BigDecimal.valueOf(request.getAmount()).negate()),
                accountUpdater.changeAmount(request.getProductDestinationId(), BigDecimal.valueOf(request.getAmount()))
        )
                .doOnSuccess(unused -> log.info("Money transfer completed successfully: from [{}] to [{}] - Amount: {}",
                        request.getProductOriginId(),
                        request.getProductDestinationId(),
                        request.getAmount()))
                .doOnError(error -> log.error("Money transfer failed: from [{}] to [{}] - Amount: {}. Reason: {}",
                        request.getProductOriginId(),
                        request.getProductDestinationId(),
                        request.getAmount(),
                        error.getMessage()));
    }
}
