package com.bank_example.product_service.infraestructure.out.persistence;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AccountRepository extends ReactiveMongoRepository<Account, String> {
    
    Mono<Long> countSavingAccountByAccountTypeAndClientId(AccountType typem, String clientId);
}
