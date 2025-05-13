package com.bank_example.product_service.infraestructure.out.persistence;

import com.bank_example.product_service.domain.generate.model.AccountResponse;
import org.springframework.stereotype.Component;


@Component
public class accountMapper {

    public AccountResponse convertFrom(Account account) {
        AccountResponse accountResponse = new AccountResponse();

        accountResponse.setId(account.getId());
        accountResponse.setAccountNumber(account.getAccountNumber());
        accountResponse.setClientId(account.getClientId());
        accountResponse.setBalance(account.getBalance().doubleValue());
        accountResponse.setOpenedDate(account.getOpenedDate());
        accountResponse.setActive(account.getActive());
        accountResponse.setCurrentWithdrawalCount(account.getCurrentWithdrawalCount());

        if(account != null) {
            accountResponse.setType( AccountResponse.TypeEnum.fromValue(account.getAccountType().name()) );
        }

        return accountResponse;
    }

}
