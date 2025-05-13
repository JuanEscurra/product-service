package com.bank_example.product_service.application.services;


import com.bank_example.product_service.application.ports.out.http.UserFinderPort;
import com.bank_example.product_service.application.ports.out.persistence.AccountCreatorPort;
import com.bank_example.product_service.application.ports.out.persistence.AccountUtils;
import com.bank_example.product_service.domain.generate.model.*;
import com.bank_example.product_service.application.ports.out.http.request.User;
import com.bank_example.product_service.application.ports.out.http.request.UserType;
import com.bank_example.product_service.domain.usecases.CreateCurrentAccountUseCase;
import com.bank_example.product_service.domain.usecases.CreateFixedTermDepositUseCase;
import com.bank_example.product_service.domain.usecases.CreateSavingAccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class DefaultProductService implements
        CreateSavingAccountUseCase,
        CreateCurrentAccountUseCase,
        CreateFixedTermDepositUseCase {

    @Value("${bank-example.business.rules.saving-accounts.max}")
    private int maxNSavingAccounts;

    @Value("${bank-example.business.rules.current-account.max}")
    private int maxNCurrentAccounts;

    @Value("${bank-example.business.rules.fixed-term-deposit.max}")
    private int maxNFixedTermDeposits;

    private final UserFinderPort userFinder;
    private final AccountCreatorPort accountCreatorPort;
    private final AccountUtils accountUtils;


    @Override
    public Mono<AccountResponse> createCurrentAccount(CreateCurrentAccountRequest request) {
        return Mono.zip(
                this.userFinder.findById(request.getClientId()),
                this.accountUtils.getCountCurrentAccounts(request.getClientId())
        ).flatMap(tuple -> {
            User user = tuple.getT1();
            Long countCurrentAccounts = tuple.getT2();
            System.out.println("countCurrentAccounts = " + countCurrentAccounts);

            if( !user.getActive() ) return Mono.error(new Exception("User is inactive"));
            if( !user.getUserType().equals(UserType.ENTERPRISE) && countCurrentAccounts >= maxNCurrentAccounts ) return Mono.error(new Exception("Count current accounts exceeded"));

            return this.accountCreatorPort.createCurrentAccount( user.getId() );
        });
    }

    @Override
    public Mono<AccountResponse> createFixedTermDeposit(CreateFixedTermDepositRequest request) {
        return Mono.zip(
                this.userFinder.findById(request.getClientId()),
                this.accountUtils.getCountFixedTermDeposit(request.getClientId())
        ).flatMap(tuple -> {
            User user = tuple.getT1();
            Long countFixedTermDeposits = tuple.getT2();

            if( !user.getUserType().equals(UserType.PERSONAL) ) return Mono.error(new Exception("Enabled for personal user"));
            if( !user.getActive() ) return Mono.error(new Exception("User is inactive"));
            if( countFixedTermDeposits >= maxNFixedTermDeposits ) return Mono.error(new Exception("Count fixed term deposits exceeded"));

            return this.accountCreatorPort.createFixedTermDeposit(request);
        });
    }

    @Override
    public Mono<AccountResponse> createSavingAccount(CreateSavingAccountRequest request) {

        return Mono.zip(
                this.userFinder.findById(request.getClientId()),
                this.accountUtils.getCountSavingAccounts(request.getClientId())
        ).flatMap(tuple -> {
            User user = tuple.getT1();
            Long countSavingAccounts = tuple.getT2();
            System.out.println("countSavingAccounts = " + countSavingAccounts);

            if( !user.getUserType().equals(UserType.PERSONAL) ) return Mono.error(new Exception("Enabled for personal user"));
            if( !user.getActive() ) return Mono.error(new Exception("User is inactive"));
            if( countSavingAccounts >= maxNSavingAccounts ) return Mono.error(new Exception("Count saving accounts exceeded"));

            return this.accountCreatorPort.createSavingAccount( user.getId() );
        });

    }

}