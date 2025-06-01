package com.bank_example.product_service.domain.models.client;

import com.bank_example.product_service.domain.models.client.value_objects.CompanyCategory;
import com.bank_example.product_service.shared.models.BasicInformation;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class Company extends BasicInformation {

    private String fiscalAddress;
    private String commercialName;
    private CompanyCategory category;
}
