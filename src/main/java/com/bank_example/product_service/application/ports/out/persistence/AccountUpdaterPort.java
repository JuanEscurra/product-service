package com.bank_example.product_service.application.ports.out.persistence;

import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface AccountUpdaterPort {

    Mono<Void> changeAmount(String productId, BigDecimal amount);
}
