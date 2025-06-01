package com.bank_example.product_service.shared.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BasicInformation {

    private String id;
    private String name;
    private String email;
    private String phone;
    private String identifier;
    private Boolean active;

}
