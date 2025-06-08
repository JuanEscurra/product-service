package com.bank_example.product_service.domain.models.product.commands;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferMoneyCommand {

    private BigDecimal amount;
    private String productOriginId;
    private String productDestId;
}
