package com.bank_example.product_service.domain.models.client;


import com.bank_example.product_service.domain.models.client.value_objects.PersonCategory;
import com.bank_example.product_service.shared.models.BasicInformation;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class Person extends BasicInformation {

    private String lastname;
    private PersonCategory category;
}
