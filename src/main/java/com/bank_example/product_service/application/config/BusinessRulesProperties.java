package com.bank_example.product_service.application.config;

import com.bank_example.product_service.application.config.models.ProductRuleGroup;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "bank-example.business.rules")
@Getter
@Setter
public class BusinessRulesProperties {
    private ProductRuleGroup savingAccounts;
    private ProductRuleGroup currentAccount;
    private ProductRuleGroup fixedTermDeposit;
    private ProductRuleGroup credit;
}