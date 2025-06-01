package com.bank_example.product_service.application.config.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ProductRule {
    private List<String> allowedClientTypes;
    private BigDecimal maintenanceFee;
    private Integer freeTransactionsLimit;
    private BigDecimal feePerExtraTransaction;
    private Integer maxAllowed;
    private BigDecimal minimumOpeningAmount;
    private BigDecimal interestRate;
}
