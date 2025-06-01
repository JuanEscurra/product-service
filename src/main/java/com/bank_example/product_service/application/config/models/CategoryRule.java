package com.bank_example.product_service.application.config.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CategoryRule {
    private BigDecimal maintenanceFee;
}
