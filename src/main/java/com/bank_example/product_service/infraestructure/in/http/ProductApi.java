package com.bank_example.product_service.infraestructure.in.http;

import com.bank_example.product_service.domain.generate.model.*;
import com.bank_example.product_service.domain.usecases.*;
import com.bank_example.product_service.infraestructure.in.api.ProductsApiDelegate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class ProductApi implements ProductsApiDelegate {

    private final CreateSavingAccountUseCase createSavingAccountUseCase;
    private final CreateCurrentAccountUseCase createCurrentAccountUseCase;
    private final CreateFixedTermDepositUseCase createFixedTermDepositUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;
    private final TransferMoneyUseCase  transferMoneyUseCase;

    @Override
    public Mono<ResponseEntity<AccountResponse>> createSavingAccount(Mono<CreateSavingAccountRequest> createSavingAccountRequest, ServerWebExchange exchange) {

        return createSavingAccountRequest
                .flatMap(this.createSavingAccountUseCase::createSavingAccount)
                .map(savingAccount -> ResponseEntity.status(HttpStatus.CREATED).body(savingAccount));
    }

    @Override
    public Mono<ResponseEntity<AccountResponse>> createCurrentAccount(Mono<CreateCurrentAccountRequest> createCurrentAccountRequest, ServerWebExchange exchange) {

        return createCurrentAccountRequest
                .flatMap(this.createCurrentAccountUseCase::createCurrentAccount)
                .map(currentAccount -> ResponseEntity.status(HttpStatus.CREATED).body(currentAccount));
    }

    @Override
    public Mono<ResponseEntity<AccountResponse>> createFixedTermDeposit(Mono<CreateFixedTermDepositRequest> createFixedTermDepositRequest, ServerWebExchange exchange) {

        return createFixedTermDepositRequest
                .flatMap(this.createFixedTermDepositUseCase::createFixedTermDeposit)
                .map(fixedTermDeposit -> ResponseEntity.status(HttpStatus.CREATED).body(fixedTermDeposit));
    }

    @Override
    public Mono<ResponseEntity<AccountResponse>> getProductById(String id, ServerWebExchange exchange) {
        return this.getProductByIdUseCase.getProductById(id)
                .map(accountResponse -> ResponseEntity.status(HttpStatus.OK).body(accountResponse));
    }


    @Override
    public Mono<ResponseEntity<Void>> transferMoney(Mono<TransferMoneyRequest> transferMoneyRequest, ServerWebExchange exchange) {
        return transferMoneyRequest
                .flatMap(this.transferMoneyUseCase::transferMoney)
                .then(Mono.fromCallable(() -> ResponseEntity.noContent().build()));
    }
}
