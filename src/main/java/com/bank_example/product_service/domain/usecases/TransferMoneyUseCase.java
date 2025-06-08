package com.bank_example.product_service.domain.usecases;

import com.bank_example.product_service.domain.generate.model.TransferMoneyRequest;
import reactor.core.publisher.Mono;

public interface TransferMoneyUseCase {

    Mono<Void> transferMoney(TransferMoneyRequest transferMoneyCommand);
}
