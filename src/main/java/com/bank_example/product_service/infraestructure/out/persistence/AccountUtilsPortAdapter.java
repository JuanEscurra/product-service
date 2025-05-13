package com.bank_example.product_service.infraestructure.out.persistence;

import com.bank_example.product_service.application.ports.out.persistence.AccountUtilsPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AccountUtilsPortAdapter implements AccountUtilsPort {

    private final SavingAccountRepository savingAccountRepository;

    @Override
    public Mono<Long> getCountSavingAccounts(String clientId) {
        return this.savingAccountRepository.countSavingAccountByAccountTypeAndClientId(AccountType.SAVING_ACCOUNT, clientId);
    }

    @Override
    public Mono<Long> getCountFixedTermDeposit(String clientId) {
        return this.savingAccountRepository.countSavingAccountByAccountTypeAndClientId(AccountType.FIXED_TERM_DEPOSIT, clientId);
    }

    @Override
    public Mono<Long> getCountCurrentAccounts(String clientId) {
        return this.savingAccountRepository.countSavingAccountByAccountTypeAndClientId(AccountType.CURRENT_ACCOUNT, clientId);
    }
}
