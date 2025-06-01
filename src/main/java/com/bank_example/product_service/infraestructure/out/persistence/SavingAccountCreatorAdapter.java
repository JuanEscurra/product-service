package com.bank_example.product_service.infraestructure.out.persistence;

import com.bank_example.product_service.application.ports.out.persistence.AccountCreatorPort;
import com.bank_example.product_service.domain.generate.model.AccountResponse;
import com.bank_example.product_service.domain.generate.model.CreateFixedTermDepositRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SavingAccountCreatorAdapter implements AccountCreatorPort {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;


    @Override
    public Mono<AccountResponse> createSavingAccount(String clientId) {
        Account account = this.createAccountInit(clientId);
        account.setAccountType(AccountType.SAVING_ACCOUNT);
        account.setBalance(BigDecimal.ZERO);

        return this.accountRepository.save(account)
                .map(this.accountMapper::convertFrom);
    }

    @Override
    public Mono<AccountResponse> createCurrentAccount(String clientId) {
        Account account = this.createAccountInit( clientId );
        account.setAccountType(AccountType.CURRENT_ACCOUNT);
        account.setBalance(BigDecimal.ZERO);

        return this.accountRepository.save(account)
                .map(this.accountMapper::convertFrom);
    }

    @Override
    public Mono<AccountResponse> createFixedTermDeposit(CreateFixedTermDepositRequest request) {
        Account account = this.createAccountInit(request.getClientId());
        account.setAccountType(AccountType.FIXED_TERM_DEPOSIT);
        account.setBalance( BigDecimal.valueOf(request.getBalance()) );
        account.setMaintenceFee(BigDecimal.ZERO);
        account.setMaturityDate(request.getMaturityDate());

        return this.accountRepository.save(account)
                .map(this.accountMapper::convertFrom);
    }



    private Account createAccountInit(String clientId) {
        Account account = new Account();
        account.setAccountNumber(UUID.randomUUID().toString());
        account.setClientId(clientId);
        account.setOpenedDate(LocalDate.now());
        account.setCurrentWithdrawalCount(0);
        account.setActive(true);

        return account;
    }
}
