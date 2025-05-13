package com.bank_example.product_service.application.ports.out.http.request;

import lombok.Data;

@Data
public class User {

    private String id;
    private UserType userType;
    private Boolean active;
}
