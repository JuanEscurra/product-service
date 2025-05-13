package com.bank_example.product_service.infraestructure.in.http;

import com.bank_example.product_service.domain.generate.model.*;
import com.bank_example.product_service.domain.usecases.CreateCurrentAccountUseCase;
import com.bank_example.product_service.domain.usecases.CreateFixedTermDepositUseCase;
import com.bank_example.product_service.domain.usecases.CreateSavingAccountUseCase;
import com.bank_example.product_service.infraestructure.in.api.CurrentAccountsApiDelegate;
import com.bank_example.product_service.infraestructure.in.api.FixedTermDepositsApiDelegate;
import com.bank_example.product_service.infraestructure.in.api.SavingAccountsApiDelegate;
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
public class ProductApi implements
        SavingAccountsApiDelegate,
        CurrentAccountsApiDelegate,
        FixedTermDepositsApiDelegate {

    private final CreateSavingAccountUseCase createSavingAccountUseCase;
    private final CreateCurrentAccountUseCase createCurrentAccountUseCase;
    private final CreateFixedTermDepositUseCase createFixedTermDepositUseCase;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return SavingAccountsApiDelegate.super.getRequest();
    }

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
}
