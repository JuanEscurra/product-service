package com.bank_example.product_service.application.config.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ProductRuleGroup {
    private ProductRule defaultRule;
    private Map<String, CategoryRule> categoryRules;
    private Map<String, ClientRule> clientRules;
}