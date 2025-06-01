package com.bank_example.product_service.application.config;

import com.bank_example.product_service.application.config.models.CategoryRule;
import com.bank_example.product_service.application.config.models.ClientRule;
import com.bank_example.product_service.application.config.models.ProductRule;
import com.bank_example.product_service.application.config.models.ProductRuleGroup;
import com.bank_example.product_service.domain.models.client.Client;
import com.bank_example.product_service.domain.models.product.ProductType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductRuleUtils {

    private final BusinessRulesProperties businessRulesProperties;

    public ProductRule getProductRule(ProductType productType, Client client) {
        ProductRuleGroup ruleGroup = this.getProductRuleGroup(productType);

        ProductRule finalRule = new ProductRule();
        BeanUtils.copyProperties(ruleGroup.getDefaultRule(), finalRule);

        if (ruleGroup.getClientRules() != null && client.getClientType() != null) {
            ClientRule clientRule = ruleGroup.getClientRules().get(client.getClientType().name());
            if (clientRule != null && clientRule.getMaxAllowed() != null) {
                finalRule.setMaxAllowed(clientRule.getMaxAllowed());
            }
        }


        if (ruleGroup.getCategoryRules() != null && client.getCategory() != null) {
            CategoryRule categoryRule = ruleGroup.getCategoryRules().get(client.getCategory());
            if (categoryRule != null && categoryRule.getMaintenanceFee() != null) {
                finalRule.setMaintenanceFee(categoryRule.getMaintenanceFee());
            }
        }

        return finalRule;
    }

    private ProductRuleGroup getProductRuleGroup(ProductType productType) {
        switch (productType) {
            case SAVING_ACCOUNT:
                return this.businessRulesProperties.getSavingAccounts();
            case CURRENT_ACCOUNT:
                return this.businessRulesProperties.getCurrentAccount();
            case FIXED_TERM_DEPOSIT:
                return this.businessRulesProperties.getFixedTermDeposit();
            case CREDIT:
                return this.businessRulesProperties.getCredit();
        }
        return null;
    }
}
