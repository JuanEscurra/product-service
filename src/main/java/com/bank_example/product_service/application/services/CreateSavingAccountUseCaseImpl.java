package com.bank_example.product_service.application.services;

import com.bank_example.product_service.application.config.ProductRuleUtils;
import com.bank_example.product_service.application.config.models.ProductRule;
import com.bank_example.product_service.application.ports.out.http.ClientFinderPort;
import com.bank_example.product_service.application.ports.out.persistence.AccountCreatorPort;
import com.bank_example.product_service.application.ports.out.persistence.AccountUtilsPort;
import com.bank_example.product_service.domain.generate.model.AccountResponse;
import com.bank_example.product_service.domain.generate.model.CreateSavingAccountRequest;
import com.bank_example.product_service.domain.models.client.Client;
import com.bank_example.product_service.domain.models.product.ProductType;
import com.bank_example.product_service.domain.usecases.CreateSavingAccountUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateSavingAccountUseCaseImpl implements CreateSavingAccountUseCase {

    private final ProductRuleUtils productRuleUtils;
    private final ClientFinderPort userFinder;
    private final AccountCreatorPort accountCreator;
    private final AccountUtilsPort accountUtils;


    @Override
    public Mono<AccountResponse> createSavingAccount(CreateSavingAccountRequest request) {

        return Mono.zip(
                this.userFinder.findById(request.getClientId()),
                this.accountUtils.getCountSavingAccounts(request.getClientId())
        ).flatMap(tuple -> {
            Client client = tuple.getT1();
            Long countSavingAccounts = tuple.getT2();
            ProductRule productRule = this.productRuleUtils.getProductRule(ProductType.SAVING_ACCOUNT, client);

            if( client.getActive() == null || !client.getActive() )
                return Mono.error(new Exception("Client is inactive"));
            if( !productRule.getAllowedClientTypes().contains(client.getClientType().name()) )
                return Mono.error(new Exception("Product is not enabled for the client"));
            if( productRule.getMaxAllowed() != -1 && countSavingAccounts >= productRule.getMaxAllowed() )
                return Mono.error(new Exception("You have exceeded the maximum savings account limit"));

            return this.accountCreator.createSavingAccount( client.getId() );
        })
                .doOnSuccess(this::printSuccessfulCreationLog)
                .doOnError(this::printreationFailedLog);

    }

    private void printSuccessfulCreationLog(AccountResponse account) {
        log.info("The fixed term deposit has been created successfully: {}", account.getAccountNumber());
    }

    private void printreationFailedLog(Throwable e) {
        log.error("An error occurred while creating the fixed term deposit: {}", e.getMessage());
    }
}
