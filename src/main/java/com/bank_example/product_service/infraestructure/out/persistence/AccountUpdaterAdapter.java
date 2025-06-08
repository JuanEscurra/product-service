package com.bank_example.product_service.infraestructure.out.persistence;

import com.bank_example.product_service.application.ports.out.persistence.AccountUpdaterPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class AccountUpdaterAdapter implements AccountUpdaterPort {

    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<Void> changeAmount(String productId, BigDecimal amount) {
        Criteria criteria = Criteria.where("_id").is(productId);
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            criteria = criteria.and("balance").gte(amount.abs()); // saldo >= |amount negativo|
        }

        Query query = Query.query(criteria);
        Update update = new Update().inc("balance", amount);

        return mongoTemplate
                .updateFirst(query, update, Account.class)
                .flatMap(result -> {
                    if (result.getModifiedCount() == 0) {
                        return Mono.error(new RuntimeException("Insufficient funds or account not found"));
                    }
                    return Mono.empty();
                });
    }
}
