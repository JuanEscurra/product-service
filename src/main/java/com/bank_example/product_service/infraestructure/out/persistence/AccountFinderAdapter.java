package com.bank_example.product_service.infraestructure.out.persistence;

import com.bank_example.product_service.application.ports.out.persistence.AccountFinderPort;
import com.bank_example.product_service.domain.generate.model.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AccountFinderAdapter implements AccountFinderPort {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public Mono<AccountResponse> findAccountById(String id) {
        return this.accountRepository.findById(id)
                .map(this.accountMapper::convertFrom);
    }
}
